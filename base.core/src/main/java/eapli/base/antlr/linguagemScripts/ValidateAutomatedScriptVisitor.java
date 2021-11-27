package eapli.base.antlr.linguagemScripts;

public class ValidateAutomatedScriptVisitor extends AutomatedScriptBaseVisitor<Object> {
    private boolean isValid;

    public ValidateAutomatedScriptVisitor() {
        this.isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public Object visitOperr(AutomatedScriptParser.OperrContext ctx) {
        this.isValid = false;
        return visitChildren ( ctx );
    }

    @Override
    public Object visitMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx) {
        this.isValid = false;
        return visitChildren ( ctx );
    }

    @Override
    public Object visitMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx) {
        this.isValid = false;
        return visitChildren ( ctx );
    }
}
