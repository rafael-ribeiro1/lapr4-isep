package eapli.base.antlr.linguagemScripts;

import java.io.IOException;

public abstract class AutomatedScriptAPI {


    protected String script;


    public AutomatedScriptAPI(String script) {
        this.script = script;
    }

    public abstract boolean validateScript();

    public abstract boolean executeScript() throws IOException;

}
