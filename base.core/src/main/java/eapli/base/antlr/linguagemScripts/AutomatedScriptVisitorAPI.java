package eapli.base.antlr.linguagemScripts;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class AutomatedScriptVisitorAPI extends AutomatedScriptAPI {

    public AutomatedScriptVisitorAPI(String script) {
        super(script);
    }

    @Override
    public boolean validateScript()  {
        AutomatedScriptLexer lexer = new AutomatedScriptLexer( CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AutomatedScriptParser parser = new AutomatedScriptParser(tokens);
        ParseTree tree = parser.start();
        ValidateAutomatedScriptVisitor visitor = new ValidateAutomatedScriptVisitor();
        visitor.visit(tree);
        return visitor.isValid() && parser.getNumberOfSyntaxErrors() == 0;
    }

    @Override
    public boolean executeScript() throws IOException {
        if (!validateScript())
            throw new IllegalArgumentException("Invalid Automated Script");
        AutomatedScriptLexer lexer = new AutomatedScriptLexer( CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AutomatedScriptParser parser = new AutomatedScriptParser(tokens);
        ParseTree tree = parser.start();
        ExecuteAutomatedScriptVisitor visitor = new ExecuteAutomatedScriptVisitor();
        visitor.visit(tree);
        return true;
    }

}
