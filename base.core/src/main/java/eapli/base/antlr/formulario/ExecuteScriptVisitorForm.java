package eapli.base.antlr.formulario;
import eapli.base.servicemanagement.domain.form.Attribute;

import java.util.HashMap;
import java.util.Map;

public class ExecuteScriptVisitorForm extends ValidacaoFormBaseVisitor<String>{

    private Map<Attribute,String> attributes;

    private boolean validScript;

    public ExecuteScriptVisitorForm(Map<Attribute, String> attributes) {
        this.attributes = new HashMap<>(attributes);
        this.validScript = false;
    }

    public boolean isFormValid() {
        return validScript;
    }

    @Override public String visitEndResult(ValidacaoFormParser.EndResultContext ctx) {
        this.validScript = convertToBoolean(visit(ctx.finalResult));
        return "";
    }

    @Override public String visitInst(ValidacaoFormParser.InstContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.instrucao_form()));
        return result.toString();
    }

    @Override public String visitInstRec(ValidacaoFormParser.InstRecContext ctx) {
        Boolean inst = convertToBoolean(visit(ctx.form()));
        if(!inst) return inst.toString();
        Boolean form = convertToBoolean(visit(ctx.instrucao_form()));
        Boolean result = form && inst;
        return result.toString();
    }

    @Override public String visitOpVerifyInstrucaoForm(ValidacaoFormParser.OpVerifyInstrucaoFormContext ctx) {
        Boolean condicao = convertToBoolean(visit( ctx.cond_verificacao()));
        Boolean validacao = convertToBoolean(visit( ctx.validacoes()));
        if (!condicao)
            return "true";
        Boolean result = condicao&&validacao;
        return result.toString();
    }

    @Override public String visitOpAndVerif(ValidacaoFormParser.OpAndVerifContext ctx) {
        boolean left = convertToBoolean(visit(ctx.left));
        boolean right = convertToBoolean(visit(ctx.right));
        Boolean result = left && right;
        return result.toString();
    }

    @Override public String visitOpOrVerif(ValidacaoFormParser.OpOrVerifContext ctx) {
        boolean left = convertToBoolean(visit(ctx.left));
        boolean right = convertToBoolean(visit(ctx.right));
        Boolean result = left || right;
        return result.toString();
    }


    @Override public String visitOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.operacao_bool()));
        return result.toString();
    }

    @Override public String visitOpValid(ValidacaoFormParser.OpValidContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.validacao()));
        return result.toString();
    }


    @Override public String visitOpValidRec(ValidacaoFormParser.OpValidRecContext ctx) {
        boolean validacao = convertToBoolean(visit(ctx.validacao()));
        boolean validacoes = convertToBoolean(visitChildren(ctx.validacoes()));
        Boolean result= validacao && validacoes;
        return result.toString();
    }

    @Override public String visitValidacao(ValidacaoFormParser.ValidacaoContext ctx) {
        Boolean result = convertToBoolean( visit(ctx.operacoes_logicas()));
        return result.toString();
    }


        @Override public String visitOpAndLogic(ValidacaoFormParser.OpAndLogicContext ctx) {
        boolean left = convertToBoolean(visit(ctx.left));
        boolean right = convertToBoolean(visit(ctx.right));
        Boolean result = left && right;
        return result.toString();
    }


    @Override public String visitOpOrLogic(ValidacaoFormParser.OpOrLogicContext ctx) {
        boolean left = convertToBoolean(visit(ctx.left));
        boolean right = convertToBoolean(visit(ctx.right));
        Boolean result = left || right;
        return result.toString();
    }

    @Override public String visitOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.operacao_bool()));
        return result.toString(); }

    @Override public String visitFuncBool(ValidacaoFormParser.FuncBoolContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.funcao_bool()));
        return result.toString();
    }


    @Override public String visitInstForm(ValidacaoFormParser.InstFormContext ctx) {
        Boolean result = convertToBoolean(visit(ctx.instrucao_form()));
        return result.toString();
    }


    @Override public String visitEmpty(ValidacaoFormParser.EmptyContext ctx) {
        String atribute = visit(ctx.atributo());
        Boolean result = atribute.trim().isEmpty();
        return result.toString();
    }

    @Override public String visitNotEmpty(ValidacaoFormParser.NotEmptyContext ctx) {
        String atribute = visit(ctx.atributo());
        Boolean result = !atribute.trim().isEmpty();
        return result.toString();
    }

    @Override public String visitMatch(ValidacaoFormParser.MatchContext ctx) {
        String atribute = visit(ctx.atributo());
        String regex = visit(ctx.regex());
        Boolean result = atribute.matches(regex);
        return result.toString();
    }

    @Override public String visitNotMatch(ValidacaoFormParser.NotMatchContext ctx) {
        String atribute = visit(ctx.atributo());
        String regex = visit(ctx.regex());
        Boolean result = !atribute.matches(regex);
        return result.toString();
    }



    @Override public String visitOpBool(ValidacaoFormParser.OpBoolContext ctx) {
        String leftStr = visit(ctx.left), rightStr = visit(ctx.right);
        Double leftNum=null,rightNum=null;
        String operador = ctx.op.getText();
        boolean isDouble;

        try{
            leftNum = Double.parseDouble(leftStr);
            isDouble=true;
        }catch (NumberFormatException e){
            isDouble=false;
        }

        try{
            rightNum = Double.parseDouble(rightStr);
            isDouble=true;
        }catch (NumberFormatException e){
            isDouble=false;
        }

        if((leftNum!=null && rightNum==null)||(leftNum==null && rightNum!=null)){
            validScript=false;
            throw new IllegalArgumentException ("Incompatible types in boolean operation");
        }

        Boolean result;

        switch(operador){
            case "<":
                result=(isDouble? leftNum.compareTo ( rightNum )<0 : leftStr.compareTo ( rightStr )<0);
                break;
            case "<=":
                result=(isDouble? leftNum.compareTo ( rightNum )<=0 : leftStr.compareTo ( rightStr )<=0);
                break;
            case ">":
                result=(isDouble? leftNum.compareTo ( rightNum )>0 : leftStr.compareTo ( rightStr )>0);
                break;
            case ">=":
                result=(isDouble? leftNum.compareTo ( rightNum )>=0 : leftStr.compareTo ( rightStr )>=0);
                break;
            case "=":
                result=(isDouble? leftNum.compareTo ( rightNum )==0 : leftStr.compareTo ( rightStr )==0);
                break;
            case "<>":
                result=(isDouble? leftNum.compareTo ( rightNum )!=0 : leftStr.compareTo ( rightStr )!=0);
                break;
            default:
                validScript=false;
                throw new IllegalArgumentException ("Illegal operator in boolean expression");
        }


        return result.toString();
    }

    @Override public String visitOpBoolAtr(ValidacaoFormParser.OpBoolAtrContext ctx) {
        return visit(ctx.atributo());
    }

    @Override public String visitOpInt(ValidacaoFormParser.OpIntContext ctx) {
        return visit(ctx.inteiro());
    }

    @Override public String visitOpStr(ValidacaoFormParser.OpStrContext ctx) {
        return ctx.str.getText().replace("\"", "");
    }


    @Override public String visitOpParen(ValidacaoFormParser.OpParenContext ctx) {
        return visit(ctx.inteiro());
    }

    @Override public String visitOpMD(ValidacaoFormParser.OpMDContext ctx) {
        String leftString = visit(ctx.left);
        String rightString = visit(ctx.right);
        try{
            double left = Double.parseDouble(leftString),
                    right = Double.parseDouble(rightString), result;
            switch (ctx.op.getText().charAt(0)) {
                case '*':
                    result = left * right;
                    break;
                case '/':
                    if (right == 0)
                        throw new IllegalArgumentException("Division by zero");
                    result = left / right;
                    break;
                default:
                    result = left % right;
            }
            return Double.toString(result);
        }catch (NumberFormatException e){
            validScript=false;
            throw new IllegalArgumentException("Invalid Format for number");
        }

    }

    @Override public String visitOpSS(ValidacaoFormParser.OpSSContext ctx) {
        String leftString = visit(ctx.left);
        String rightString = visit(ctx.right);

        try{
                double left = Double.parseDouble(leftString),
                        right = Double.parseDouble(rightString), result;

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
                return Double.toString(result);
            }catch (NumberFormatException e){
                validScript=false;
                throw new IllegalArgumentException("Invalid Format for number");
            }

    }

    @Override public String visitOpLen(ValidacaoFormParser.OpLenContext ctx) {
        String atributo = visit(ctx.attr);
        int tamanho = atributo.length();
        return Integer.toString(tamanho);
    }


    @Override public String visitOpAtr(ValidacaoFormParser.OpAtrContext ctx) {
        String attribute = visit(ctx.atributo());
        if (!attribute.trim().isEmpty()) {
            return attribute;
        }
       return Double.toString(Double.NaN);
    }

    @Override public String visitOpNum(ValidacaoFormParser.OpNumContext ctx) {
        Double result;
        try{
            result = Double.parseDouble(ctx.num.getText());
        }catch (NumberFormatException | NullPointerException e){
            validScript=false;
            throw new IllegalArgumentException("Invalid Format for number");
        }
        return result.toString();
    }


    @Override public String visitAttribute(ValidacaoFormParser.AttributeContext ctx) {
        String name = ctx.name.getText();
        for (Attribute a : attributes.keySet()) {
            if(a.name().equals(name)) {
                return attributes.get(a);
            }
        }
        validScript=false;
        throw new IllegalArgumentException ("Attribute doesn't exist");
    }

    @Override public String visitRegex(ValidacaoFormParser.RegexContext ctx) {
        return ctx.reg.getText().replace("\"", "");
    }


    @Override public String visitOperr(ValidacaoFormParser.OperrContext ctx) {
        validScript=false;
        throw new IllegalArgumentException ("Syntax error");
    }

    private Boolean convertToBoolean(final String input){
        if(input==null||input.equalsIgnoreCase("true"))return true;
        if(input.equalsIgnoreCase("false"))return false;
        throw new IllegalArgumentException("Boolean nao reconhecido");
    }


}
