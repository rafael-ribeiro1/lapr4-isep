package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Map;

public class ValidateFormListenerAPI extends ValidateFormAPI {

    public ValidateFormListenerAPI(String script, Map<Attribute, String> attributes) {
        super(script, attributes);
    }

    public ValidateFormListenerAPI(String script) {
        super(script);
    }

    @Override
    public boolean validateScript() {
        ValidacaoFormLexer lexer = new ValidacaoFormLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ValidacaoFormParser parser = new ValidacaoFormParser(tokens);
        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        ValidateScriptListenerForm listener = new ValidateScriptListenerForm(attributes);
        walker.walk(listener, tree);
        return listener.isValid() && parser.getNumberOfSyntaxErrors() == 0;
    }

    @Override
    public boolean executeScript() throws IllegalArgumentException {
        if (!validateScript())
            throw new IllegalArgumentException("Invalid script");

        ValidacaoFormLexer lexer = new ValidacaoFormLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ValidacaoFormParser parser = new ValidacaoFormParser(tokens);
        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExecuteScriptListenerForm listener = new ExecuteScriptListenerForm(attributes);
        walker.walk(listener, tree);
        return listener.isFormValid();
    }

}
