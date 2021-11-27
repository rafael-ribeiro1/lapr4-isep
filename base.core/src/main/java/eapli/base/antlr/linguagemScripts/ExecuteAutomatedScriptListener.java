package eapli.base.antlr.linguagemScripts;
import eapli.base.utils.EmailHandler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExecuteAutomatedScriptListener extends AutomatedScriptBaseListener{

    private StringBuilder stringBuilder;
    private  Map<String, Double> variaveis;
    private  Stack<Double> decimalStack = new Stack<>();
    private  Stack<Boolean> booleanStack = new Stack<>();
    private  Stack<String> stringStack = new Stack<>();
    private boolean permissionToExecute = true;


    public ExecuteAutomatedScriptListener() {
        this.stringBuilder = new StringBuilder();
        this.variaveis = new HashMap<>();
    }

    @Override public void exitCondicaoIf(AutomatedScriptParser.CondicaoIfContext ctx) {
        permissionToExecute = true;
    }

    @Override public void exitVerificarCondicaoIf(AutomatedScriptParser.VerificarCondicaoIfContext ctx) {
        permissionToExecute = booleanStack.pop();
    }

    @Override public void exitAndCondition(AutomatedScriptParser.AndConditionContext ctx) {
        boolean right = booleanStack.pop();
        boolean left = booleanStack.pop();
        booleanStack.push(left&&right);
    }

    @Override public void exitOrCondition(AutomatedScriptParser.OrConditionContext ctx) {
        boolean right = booleanStack.pop();
        boolean left = booleanStack.pop();
        booleanStack.push(left||right);
    }


    @Override public void exitRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx) {

        boolean result;
        Double right = obterValor(ctx.right.getText());
        Double left=obterValor(ctx.left.getText());

        String op = ctx.OPERADOR_COMPARAR().toString();

        switch(op){
            case "<":
                result=left.compareTo(right)<0;
                break;
            case "<=":
                result= left.compareTo ( right )<=0;
                break;
            case ">":
                result= left.compareTo ( right )>0;
                break;
            case ">=":
                result= left.compareTo ( right )>=0;
                break;
            case "==":
                result= left.compareTo ( right )==0;
                break;
            case "<>":
                result=left.compareTo ( right )!=0;
                break;
            default:
                throw new IllegalArgumentException ("Illegal operator in boolean expression");
        }
        booleanStack.push(result);

    }

    @Override public void enterSendEmail(AutomatedScriptParser.SendEmailContext ctx) {
        if(!permissionToExecute) return;
        stringStack.clear();
    }

    @Override public void exitSendEmail(AutomatedScriptParser.SendEmailContext ctx) {
        if(!permissionToExecute) return;
        EmailHandler emailHandler = new EmailHandler();
        String conteudo = stringStack.pop();
        String assunto = stringStack.pop();
        emailHandler.sendEmail(removerAspas(ctx.destinatario.getText()),
                assunto, conteudo);
    }

    @Override public void exitReadFile(AutomatedScriptParser.ReadFileContext ctx) {
        if(!permissionToExecute) return;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            String filePath = removerAspas(ctx.nomeFicheiro.FILE_PATH().getText());
            String id = removerAspas(ctx.id.getText());

            Document doc = db.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            String base = removerAspas(ctx.base.getText());

            NodeList list = doc.getElementsByTagName(base);


            for(int i =0 ; i< list.getLength() ; i++){
                Node node = list.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE ){
                    Element obtained = (Element) node;

                    String identfier = obtained.getElementsByTagName("id").item(0).getTextContent();

                    if(!identfier.equals(id)) continue;

                    String elemento = removerAspas(ctx.elemento.getText());

                    Node a = obtained.getElementsByTagName(elemento).item(0);
                    String desconto = a.getTextContent();
                    decimalStack.push(Double.parseDouble(removerAspas(desconto)));
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException | NullPointerException | NumberFormatException e) {
            throw new IllegalArgumentException("O ficheiro nao possui o elemento passado como parametro");
        }

    }

    @Override public void enterProcessarString(AutomatedScriptParser.ProcessarStringContext ctx) {
        if(!permissionToExecute) return;
        stringBuilder = new StringBuilder();
    }

    @Override public void exitProcessarString(AutomatedScriptParser.ProcessarStringContext ctx) {
        if(!permissionToExecute) return;
        stringStack.push(stringBuilder.toString());
    }

    @Override public void exitSkip(AutomatedScriptParser.SkipContext ctx) {
        if(!permissionToExecute) return;
        stringBuilder.append(ctx.word.getText()).append(" ");
    }

    @Override
    public void exitRealizarOutput(AutomatedScriptParser.RealizarOutputContext ctx) {
        if(!permissionToExecute) return;
        String nome = ctx.nome.getText();
      String content = String.format("%.2f ",obterValorVariavel(nome));
      stringBuilder.append(content);
      return;
    }

    @Override
    public void exitRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx) {
        if(!permissionToExecute) return;
        try{
          double right = decimalStack.pop();
          double left = decimalStack.pop();
            double result;
            switch (ctx.op.getText().charAt(0)) {
                case '*':
                    result = left * right;
                    break;
                case '/':
                    result = left / right;
                    break;
                default:
                    result = left % right;
            }
            decimalStack.push(result);
        }catch (NullPointerException | EmptyStackException e){
            return;
        }
    }

    @Override public void exitRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx) {
        if(!permissionToExecute) return;
        try{
            double right = decimalStack.pop();
            double left = decimalStack.pop();
            double result;
            switch (ctx.op.getText().charAt(0)) {
                case '+':
                    result = left + right;
                    break;
                case '-':
                    result = left - right;
                    break;
                default:
                    result = 0;
            }
            decimalStack.push(result);
        }catch (NullPointerException | EmptyStackException e){
            return;
        }
    }

    @Override public void exitReturnProprioNumero(AutomatedScriptParser.ReturnProprioNumeroContext ctx) {
        if(!permissionToExecute) return;
        decimalStack.push(Double.valueOf(ctx.num.getText()));
    }

    @Override public void exitDevolver_valor_variavel(AutomatedScriptParser.Devolver_valor_variavelContext ctx) {
        if(!permissionToExecute) return;
        decimalStack.push(obterValorVariavel(ctx.nome.getText()));
    }


    @Override public void exitReturnPercentagem(AutomatedScriptParser.ReturnPercentagemContext ctx) {
        if(!permissionToExecute) return;
        Double result = Double.parseDouble(ctx.num_percentagem.NUM().getText())/100;
        decimalStack.push(result);
    }

    @Override
    public void exitColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx) {
        if(!permissionToExecute) return;
        try{
            double l = decimalStack.pop();
            variaveis.put(ctx.nome.getText(),l);
        }catch (EmptyStackException e){
            return;
        }
    }

    @Override public void exitColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx) {
        if(!permissionToExecute) return;
        try{
            double result = decimalStack.pop();
            variaveis.put(ctx.nome.getText(), result);
        }catch(EmptyStackException e){
            return;
        }
    }

    @Override public void enterOperr(AutomatedScriptParser.OperrContext ctx) {
        throw new IllegalArgumentException ("Syntax Error: Error on decimal");
    }

    @Override public void enterMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx) {
        throw new IllegalArgumentException ("Syntax Error: Missing Aspas on string");
    }

    @Override public void enterMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx) {
        throw new IllegalArgumentException("Syntax Error: Missing ponto e virgula");
    }

    private String removerAspas(String s){
        return s.replace('"', ' ').trim();
    }

    private boolean isNomeVariavel(String var){
        return var.matches("\\Q$\\E[A-Za-z0-9_?!:\\u00C0-\\u00FF]+");
    }

    private double obterValor(String var){
        try {
            if (!isNomeVariavel(var)) {
                return decimalStack.pop();
            }else {
                return obterValorVariavel(var);
            }
        }catch(EmptyStackException e){
            return 0;
        }
    }

    private Double obterValorVariavel(String nomeVariavel){
        Double result=0.0;
        try{
            result = variaveis.get(nomeVariavel);
            if(result== null) throw new IllegalArgumentException("The mentionted var does not exist");
            if(result== decimalStack.peek()) decimalStack.pop();
        } catch (EmptyStackException e){
        }
        return result;
    }
}
