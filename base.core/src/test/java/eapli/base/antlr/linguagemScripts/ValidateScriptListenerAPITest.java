package eapli.base.antlr.linguagemScripts;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ValidateScriptListenerAPITest {

    @Test
    public void testValidateScriptWithMissingQuotes() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI("SCRIPT{ script; }");
        boolean result = api.validateScript();
        assertFalse(result);
    }
    @Test
    public void testValidateScriptWithoutMissingQuotes() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI("SCRIPT{ \"script\" }");
        boolean result = api.validateScript();
        assertFalse(result);
    }

    @Test
    public void testValidScript() throws IOException {

        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/smallScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(content);
        boolean result = api.validateScript();
        assertTrue(result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteInvalidScript() throws IOException {
        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/invalidScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(content);
        api.executeScript();

    }


    @Test
    public void testValidateScriptWithSemicolon() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI("SCRIPT{ $valor1 = (13+1)/2; }");
        boolean result = api.validateScript();
        assertTrue(result);
    }
    @Test
    public void testValidateScriptWithoutSemicolon() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI("SCRIPT{ $valor1 = (13+1)/2 }");
        boolean result = api.validateScript();
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteScriptObtainUnknownVariables() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI("SCRIPT{ $valor1 = (13+1)/2 + $valor2; }");
        api.executeScript();
    }

    @Test
    public void testValidateCompleteScript() throws IOException {

        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/completeScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(content);
        boolean result = api.validateScript();
        assertTrue(result);

    }

    @Test
    public void testExecuteCompleteScript() throws IOException {

        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/completeScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(content);
        boolean result = api.executeScript();
        assertTrue(result);

    }




}
