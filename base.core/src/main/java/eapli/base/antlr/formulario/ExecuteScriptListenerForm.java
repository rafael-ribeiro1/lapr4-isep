package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExecuteScriptListenerForm extends ValidacaoFormBaseListener {
    private Map<Attribute,String> attributes;
    private String regex;
    private String attribute;
    private final Stack<Double> opMathStack=new Stack<> ();
    private final Stack<Object> opBoolStack=new Stack<>();
    private final Stack<Boolean> opLogicStack=new Stack<>();
    private final Stack<Boolean> opVerifStack=new Stack<>();
    private final Stack<Boolean> validStack=new Stack<>();
    private final Stack<Boolean> global=new Stack<>();

    public ExecuteScriptListenerForm(Map<Attribute, String> attributes) {
        this.attributes = new HashMap<> (attributes);
    }

    public boolean isFormValid() {
        return global.peek();
    }

    @Override
    public void exitInst(ValidacaoFormParser.InstContext ctx) {
        boolean verif = opVerifStack.pop();
        if (!verif)
            global.push(true);
        else
            global.push(validStack.pop());
        validStack.clear();
        opVerifStack.clear();
    }

    @Override
    public void exitInstRec(ValidacaoFormParser.InstRecContext ctx) {
        boolean verif = opVerifStack.pop();
        if (!verif)
            global.push(true);
        else
            global.push(validStack.pop() && global.pop());
        validStack.clear();
        opVerifStack.clear();
    }

    @Override
    public void exitOpAndVerif(ValidacaoFormParser.OpAndVerifContext ctx) {
        boolean right = opVerifStack.pop();
        boolean left = opVerifStack.pop();
        opVerifStack.push(left && right);
    }

    @Override
    public void exitOpOrVerif(ValidacaoFormParser.OpOrVerifContext ctx) {
        boolean right = opVerifStack.pop();
        boolean left = opVerifStack.pop();
        opVerifStack.push(left || right);
    }

    @Override
    public void exitOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx) {
        try {
            opVerifStack.push((Boolean) opBoolStack.peek());
        } catch (ClassCastException e) {
            throw new IllegalArgumentException ("Invalid operation");
        }
    }

    @Override
    public void exitOpValid(ValidacaoFormParser.OpValidContext ctx) {
        validStack.push(opLogicStack.peek());
    }

    @Override
    public void exitOpValidRec(ValidacaoFormParser.OpValidRecContext ctx) {
        validStack.push(opLogicStack.peek() && validStack.pop());
    }

    @Override
    public void enterValidacao(ValidacaoFormParser.ValidacaoContext ctx) {
        opLogicStack.clear();
    }

    @Override
    public void exitOpAndLogic(ValidacaoFormParser.OpAndLogicContext ctx) {
        boolean right = opLogicStack.pop();
        boolean left = opLogicStack.pop();
        opLogicStack.push(left && right);
    }

    @Override
    public void exitOpOrLogic(ValidacaoFormParser.OpOrLogicContext ctx) {
        boolean right = opLogicStack.pop();
        boolean left = opLogicStack.pop();
        opLogicStack.push(left || right);
    }

    @Override
    public void exitInstForm(ValidacaoFormParser.InstFormContext ctx) {
        opLogicStack.push(validStack.pop());
    }

    @Override
    public void exitOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx) {
        try {
            opLogicStack.push((Boolean) opBoolStack.peek());
        } catch (ClassCastException e) {
            throw new IllegalArgumentException ("Invalid operation");
        }
    }

    @Override
    public void exitEmpty(ValidacaoFormParser.EmptyContext ctx) {
        opLogicStack.push(attribute.trim().isEmpty());
    }

    @Override
    public void exitNotEmpty(ValidacaoFormParser.NotEmptyContext ctx) {
        opLogicStack.push(!attribute.trim().isEmpty());
    }

    @Override
    public void exitMatch(ValidacaoFormParser.MatchContext ctx) {
        opLogicStack.push(attribute.matches(regex));
    }

    @Override
    public void exitNotMatch(ValidacaoFormParser.NotMatchContext ctx) {
        opLogicStack.push(!attribute.matches(regex));
    }

    @Override
    public void enterOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx) {
        opBoolStack.clear ();
    }

    @Override
    public void enterOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx) {
        opBoolStack.clear ();
    }

    @Override
    public void exitOpBool(ValidacaoFormParser.OpBoolContext ctx) {
        Object right=opBoolStack.pop ();
        Object left=opBoolStack.pop ();
        Double leftNum=null,rightNum=null;
        String leftStr=null, rightStr=null;
        String op=ctx.op.getText ();
        boolean isDouble=false;
        try{
            leftStr=(String)left;
        }catch (ClassCastException e){
            leftNum=(Double)left;
            isDouble=true;
        }
        try{
            rightStr=(String)right;
        }catch (ClassCastException e){
            rightNum=(Double)right;
            isDouble=true;
        }
        if((leftNum!=null && rightNum==null)||(leftNum==null && rightNum!=null) ||
        (leftStr!=null && rightStr==null)||(leftStr==null && rightStr!=null) ){
            throw new IllegalArgumentException ("Incompatible types in boolean operation");
        }
        if (leftNum == null && rightNum == null && leftStr == null && rightStr == null)
            throw new IllegalArgumentException ("Invalid operators");
        Boolean result;
        switch(op){
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
                throw new IllegalArgumentException ("Illegal operator in boolean expression");
        }
        opBoolStack.push ( result );
    }

    @Override
    public void exitOpStr(ValidacaoFormParser.OpStrContext ctx) {
        opBoolStack.push(ctx.str.getText().replace("\"", ""));
    }

    @Override
    public void exitOpBoolAtr(ValidacaoFormParser.OpBoolAtrContext ctx) {
        opBoolStack.push(attribute);
    }

    @Override
    public void exitOpInt(ValidacaoFormParser.OpIntContext ctx) {
        opBoolStack.push(opMathStack.peek());
    }

    @Override
    public void enterOpInt(ValidacaoFormParser.OpIntContext ctx) {
        opMathStack.clear();
    }

    @Override
    public void exitOpSS(ValidacaoFormParser.OpSSContext ctx) {
        double right = opMathStack.pop();
        double left = opMathStack.pop();
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
        opMathStack.push(result);
    }

    @Override
    public void exitOpMD(ValidacaoFormParser.OpMDContext ctx) {
        double right = opMathStack.pop();
        double left = opMathStack.pop();
        double result;
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
        opMathStack.push(result);
    }

    @Override
    public void exitOpAtr(ValidacaoFormParser.OpAtrContext ctx) {
        if (!attribute.trim().isEmpty())
            opMathStack.push ( Double.parseDouble ( attribute ));
        else
            opMathStack.push(Double.NaN);
    }

    @Override
    public void exitOpNum(ValidacaoFormParser.OpNumContext ctx) {
        opMathStack.push ( Double.parseDouble ( ctx.num.getText () ));
    }

    @Override
    public void exitOpLen(ValidacaoFormParser.OpLenContext ctx) {
        opMathStack.push ( (double)attribute.length ());
    }

    @Override
    public void enterAttribute(ValidacaoFormParser.AttributeContext ctx) {
        String name = ctx.name.getText();
        for (Attribute attribute : attributes.keySet()) {
            if (name.equals(attribute.name())){
                this.attribute=attributes.get ( attribute );
                return;
            }
        }
        throw new IllegalArgumentException ("Did not find the attribute");
    }

    @Override
    public void enterRegex(ValidacaoFormParser.RegexContext ctx) {
        this.regex=ctx.reg.getText().replace("\"", "");
    }

    @Override
    public void enterOperr(ValidacaoFormParser.OperrContext ctx) {
        throw new IllegalArgumentException ("Syntax error");
    }
}
