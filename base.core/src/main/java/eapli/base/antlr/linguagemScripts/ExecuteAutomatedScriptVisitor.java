package eapli.base.antlr.linguagemScripts;

import eapli.base.utils.EmailHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExecuteAutomatedScriptVisitor extends AutomatedScriptBaseVisitor<Object>{
    private final Map<String, Double> variaveis=new HashMap<> ();

    @Override
    public Object visitCondicaoIf(AutomatedScriptParser.CondicaoIfContext ctx) {
        Boolean cond = (Boolean) visit(ctx.cond);
        if (cond)
            return visit(ctx.condicoes);
        return true;
    }

    @Override
    public Object visitVerificarCondicaoIf(AutomatedScriptParser.VerificarCondicaoIfContext ctx) {
        return visit(ctx.cond);
    }

    @Override
    public Object visitPrioridade(AutomatedScriptParser.PrioridadeContext ctx) {
        return visit(ctx.cond);
    }

    @Override
    public Object visitAndCondition(AutomatedScriptParser.AndConditionContext ctx) {
        Boolean left = (Boolean) visit(ctx.left);
        Boolean right = (Boolean) visit(ctx.right);
        return left && right;
    }

    @Override
    public Object visitOrCondition(AutomatedScriptParser.OrConditionContext ctx) {
        Boolean left = (Boolean) visit(ctx.left);
        Boolean right = (Boolean) visit(ctx.right);
        return left || right;
    }

    @Override
    public Object visitOperacaoBoolean(AutomatedScriptParser.OperacaoBooleanContext ctx) {
        return visit(ctx.bool);
    }

    @Override
    public Object visitRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx) {
        Double right = (Double)visit(ctx.right);
        Double left=(Double)visit(ctx.left);
        String op = ctx.op.getText ();

        switch(op) {
            case "<":
                return left.compareTo ( right ) < 0;
            case "<=":
                return left.compareTo ( right ) <= 0;
            case ">":
                return left.compareTo ( right ) > 0;
            case ">=":
                return left.compareTo ( right ) >= 0;
            case "==":
                return left.compareTo ( right ) == 0;
            case "<>":
                return left.compareTo ( right ) != 0;
        }
        throw new IllegalArgumentException ( "Illegal operator in boolean expression" );
    }

    @Override
    public Object visitSendEmail(AutomatedScriptParser.SendEmailContext ctx) {
        String dest=ctx.destinatario.getText ();
        String assunto=removerAspas ((String)visit(ctx.assunto));
        String corpo=removerAspas ((String)visit(ctx.corpo));
        EmailHandler emailHandler = new EmailHandler();
        return emailHandler.sendEmailWithRetry (removerAspas(dest),assunto, corpo);
    }

    @Override
    public Object visitReadFile(AutomatedScriptParser.ReadFileContext ctx) {
        String pathFicheiro=(String)visit(ctx.nomeFicheiro);
        String base=removerAspas((String)visit(ctx.base));
        String id=removerAspas((String)visit(ctx.id));
        String elemento=removerAspas((String)visit(ctx.elemento));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature( XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File (pathFicheiro));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName(base);

            for(int i =0 ; i< list.getLength() ; i++){
                Node node = list.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE ){
                    Element obtained = (Element) node;
                    String identfier = obtained.getElementsByTagName("id").item(0).getTextContent();
                    if(!identfier.equals(id))
                        continue;
                    Node a = obtained.getElementsByTagName(elemento).item(0);
                    String desconto = a.getTextContent();
                    return Double.parseDouble ( removerAspas ( desconto ) );
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | NullPointerException | NumberFormatException e) {
            throw new IllegalArgumentException("O ficheiro nao possui o elemento passado como parametro");
        }
        return null;
    }

    @Override
    public Object visitProcessarString(AutomatedScriptParser.ProcessarStringContext ctx) {
        return (String)visit(ctx.cont);
    }

    @Override
    public Object visitCheckContent(AutomatedScriptParser.CheckContentContext ctx) {
        if(ctx.cont!=null){
            return (String)visit(ctx.cont)+" "+visit ( ctx.out );
        }
        return visit ( ctx.out );
    }

    @Override
    public Object visitSkip(AutomatedScriptParser.SkipContext ctx) {
        if(ctx.cont!=null){
            return (String)visit(ctx.cont)+" "+ctx.word.getText ();
        }
        return ctx.word.getText ();
    }

    @Override
    public Object visitRealizarOutput(AutomatedScriptParser.RealizarOutputContext ctx) {
        return getVariable ((String)visit(ctx.nome)).toString ();
    }

    @Override
    public Object visitRealizarCalculoPrioritario(AutomatedScriptParser.RealizarCalculoPrioritarioContext ctx) {
        return visit(ctx.dec);
    }

    @Override
    public Object visitRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx) {
        double left = (Double) visit(ctx.left);
        double right = (Double) visit(ctx.right);
        switch (ctx.op.getText().charAt(0)) {
            case '*': return left * right;
            case '/': return left / right;
            case '#': return left % right;
        }
        throw new IllegalArgumentException("Invalid operator");
    }

    @Override
    public Object visitRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx) {
        double left = (Double) visit(ctx.left);
        double right = (Double) visit(ctx.right);
        switch (ctx.op.getText().charAt(0)) {
            case '+': return left + right;
            case '-': return left - right;
        }
        throw new IllegalArgumentException("Invalid operator");
    }

    @Override
    public Object visitReturnProprioNumero(AutomatedScriptParser.ReturnProprioNumeroContext ctx) {
        return Double.parseDouble(ctx.num.getText());
    }

    @Override
    public Object visitReturnPercentagem(AutomatedScriptParser.ReturnPercentagemContext ctx) {
        return visit(ctx.num_percentagem);
    }

    @Override
    public Object visitDevolver_valor_variavel(AutomatedScriptParser.Devolver_valor_variavelContext ctx) {
        String nomeVar = (String) visit(ctx.nome);
        return getVariable(nomeVar);
    }

    @Override
    public Object visitPercentagem_num(AutomatedScriptParser.Percentagem_numContext ctx) {
        return Double.parseDouble(ctx.num.getText()) / 100.0d;
    }

    @Override
    public Object visitNome_ficheiro(AutomatedScriptParser.Nome_ficheiroContext ctx) {
        return ctx.path.getText();
    }

    @Override
    public Object visitColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx) {
        String nomeVar = (String) visit(ctx.nome);
        Double value = (Double) visit(ctx.valor);
        variaveis.put(nomeVar, value);
        return value;
    }

    @Override
    public Object visitColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx) {
        String nomeVar = (String) visit(ctx.nome);
        Double value = (Double) visit(ctx.valor);
        variaveis.put(nomeVar, value);
        return value;
    }

    @Override
    public Object visitNome_variavel(AutomatedScriptParser.Nome_variavelContext ctx) {
        return ctx.word.getText();
    }

    @Override
    public Object visitMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx) {
        throw new IllegalArgumentException ("Missing semi-colon");
    }

    @Override
    public Object visitMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx) {
        throw new IllegalArgumentException ("Missing double quotes");
    }

    @Override
    public Object visitOperr(AutomatedScriptParser.OperrContext ctx) {
        throw new IllegalArgumentException ("Error in mathematical expression");
    }

    private String removerAspas(String s){
        return s.replace('"', ' ').trim();
    }

    private Double getVariable(String nomeVar) {
        Double val = variaveis.get(nomeVar);
        if (val == null)
            throw new IllegalArgumentException("Variable not initialized");
        return val;
    }

}
