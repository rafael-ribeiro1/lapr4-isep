package eapli.base.antlr.linguagemScripts;

public class ValidateAutomatedScriptListener extends AutomatedScriptBaseListener{

    private boolean isValid;

    public ValidateAutomatedScriptListener() {
        isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override public void enterOperr(AutomatedScriptParser.OperrContext ctx) {
       isValid = false;
    }

    @Override public void enterMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx) {
        isValid = false;
    }

    @Override public void enterMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx) {
        isValid = false;
    }
}
