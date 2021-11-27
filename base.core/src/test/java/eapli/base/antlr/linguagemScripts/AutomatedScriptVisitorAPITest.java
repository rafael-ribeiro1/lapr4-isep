package eapli.base.antlr.linguagemScripts;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AutomatedScriptVisitorAPITest {

    @Test
    public void testValidateScriptWithMissingQuotes(){
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI("SCRIPT{ script; }");
        boolean result = api.validateScript();
        assertFalse(result);
    }
    @Test
    public void testValidateScriptWithoutMissingQuotes() {
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI("SCRIPT{ \"script\" }");
        boolean result = api.validateScript();
        assertFalse(result);
    }
    @Test
    public void testValidScript() throws IOException {
        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/smallScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI(content);
        boolean result = api.validateScript();
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteInvalidScript() throws IOException {
        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/invalidScript.txt"), StandardCharsets.US_ASCII);
        new AutomatedScriptVisitorAPI(content).executeScript ();
    }

    @Test
    public void testValidateScriptWithSemicolon() {
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI("SCRIPT{ $valor1 = (13+1)/2; }");
        boolean result = api.validateScript();
        assertTrue(result);
    }
    @Test
    public void testValidateScriptWithoutSemicolon() {
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI("SCRIPT{ $valor1 = (13+1)/2 }");
        boolean result = api.validateScript();
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteScriptObtainUnknownVariables() throws IOException {
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI("SCRIPT{ $valor1 = (13+1)/2 + $valor2; }");
        api.executeScript();
    }

    @Test
    public void testValidateCompleteScript() throws IOException {
        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/completeScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI(content);
        boolean result = api.validateScript();
        assertTrue(result);

    }

    @Test
    public void testExecuteCompleteScript() throws IOException {
        String content = Files.readString(Paths.get("src/test/java/eapli/base/antlr/linguagemScripts/scripts/completeScript.txt"), StandardCharsets.US_ASCII);
        AutomatedScriptAPI api = new AutomatedScriptVisitorAPI(content);
        boolean result = api.executeScript();
        assertTrue(result);

    }
}