package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.Map;

public class ValidateFormVisitorAPI extends ValidateFormAPI {

    public ValidateFormVisitorAPI(String script, Map<Attribute, String> attributes) {
        super(script, attributes);
    }

    public ValidateFormVisitorAPI(String script) {
        super(script);
    }

    @Override
    public boolean validateScript() {
        ValidacaoFormLexer lexer = new ValidacaoFormLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ValidacaoFormParser parser = new ValidacaoFormParser(tokens);
        ParseTree tree = parser.start();
        ValidateScriptVisitorForm visitor = new ValidateScriptVisitorForm(attributes);
        visitor.visit(tree);
        return visitor.isValid() && parser.getNumberOfSyntaxErrors() == 0;
    }

    @Override
    public boolean executeScript() {
        if (!validateScript())
            throw new IllegalArgumentException("Invalid script");


        ValidacaoFormLexer lexer = new ValidacaoFormLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ValidacaoFormParser parser = new ValidacaoFormParser(tokens);
        ParseTree tree = parser.start();
        ExecuteScriptVisitorForm visitor = new ExecuteScriptVisitorForm(attributes);
        visitor.visit(tree);
        return visitor.isFormValid() && parser.getNumberOfSyntaxErrors() == 0;
    }

}









