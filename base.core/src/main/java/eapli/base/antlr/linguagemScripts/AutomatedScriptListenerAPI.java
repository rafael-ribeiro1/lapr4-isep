package eapli.base.antlr.linguagemScripts;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class AutomatedScriptListenerAPI extends AutomatedScriptAPI{

    public AutomatedScriptListenerAPI(String script) {
        super(script);
    }

    @Override
    public boolean validateScript() {
        AutomatedScriptLexer lexer = new AutomatedScriptLexer(CharStreams.fromString(script));
        CommonTokenStream token = new CommonTokenStream(lexer);
        AutomatedScriptParser parser = new AutomatedScriptParser(token);
        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        ValidateAutomatedScriptListener listener = new ValidateAutomatedScriptListener();
        walker.walk(listener,tree);
        return parser.getNumberOfSyntaxErrors()==0 && listener.isValid();
    }

    @Override
    public boolean executeScript() {
        if (!validateScript()){
            throw new IllegalArgumentException("Invalid Automated Script");
        }
        AutomatedScriptLexer lexer = new AutomatedScriptLexer(CharStreams.fromString(script));
        CommonTokenStream token = new CommonTokenStream(lexer);
        AutomatedScriptParser parser = new AutomatedScriptParser(token);
        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExecuteAutomatedScriptListener listener = new ExecuteAutomatedScriptListener();
        walker.walk(listener,tree);
        return parser.getNumberOfSyntaxErrors()==0 ;
    }
}
