package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateFormVisitorAPITest {


    @Test
    public void testValidateScriptWithValidScript() {
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { 3 = 3 -> [5 > 3]; }");
        boolean result = api.validateScript();

        assertTrue(result);
    }

    @Test
    public void testValidateScriptWithInvalidScript() {
        ValidateFormAPI api = new ValidateFormVisitorAPI("formabc { ; }");
        boolean result = api.validateScript();

        assertFalse(result); // Script com erros sintáticos/léxicos
    }

    @Test
    public void testValidateScriptWithValidScriptWithAttributes() {
        Map<Attribute, String> attributes = createSimpleAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoA = 3 -> [5 > 3]; }", attributes);
        boolean result = api.validateScript();

        assertTrue(result);
    }

    @Test
    public void testValidateScriptWithInvalidScriptWithAttributes() {
        Map<Attribute, String> attributes = createSimpleAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoB = 3 -> [5 > 3]; }", attributes);
        boolean result = api.validateScript();

        assertFalse(result); // Não existe CampoB
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteScriptWithInvalidScript() {
        ValidateFormAPI api = new ValidateFormVisitorAPI("formabc { ; }");
        api.executeScript(); // IllegalArgumentException por script com erros sintáticos/léxicos
    }

    @Test
    public void testExecuteScriptForSimpleValidForm() {
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { 3 = 3 -> [5 > 3]; 1 = 2 -> [3 = 3][1 > 2]; }");
        boolean result = api.executeScript();

        assertTrue(result);
    }

    @Test
    public void testExecuteScriptForSimpleInvalidForm() {
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { 3 = 3 -> [5 > 3]; 1 = 1 -> [3 = 3][1 > 2]; }");
        boolean result = api.executeScript();

        assertFalse(result); // Segunda instrução não tem todas as validações verdadeiras
    }

    @Test
    public void testExecuteScriptForSimpleValidFormWithAttributes() {
        Map<Attribute, String> attributes = createSimpleAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoA = 123 -> [5 > 3][isNotEmpty(CampoA)]; }", attributes);
        boolean result = api.executeScript();

        assertTrue(result);
    }

    @Test
    public void testExecuteScriptForSimpleInvalidFormWithAttributes() {
        Map<Attribute, String> attributes = createSimpleAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoA = 123 -> [5 > 3][isEmpty(CampoA)]; }", attributes);
        boolean result = api.executeScript();

        assertFalse(result); // CampoA não está vazia
    }

    @Test
    public void testExecuteScriptForCompleteValidFormWithAttributes() {
        Map<Attribute, String> attributes = createCompleteAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoA = 123 -> [CampoC > 3 -> [5 > 3]][isNotEmpty(CampoB)]; str(CampoB) <> \"abc\" -> [isEmpty(CampoC)]; str(CampoB) = \"abc\" -> [match(CampoA, \"[0-9]+\") OR isEmpty(CampoC)]; CampoA > 100 -> [length(CampoB) = 3 AND isEmpty(CampoC)]; }", attributes);
        boolean result = api.executeScript();
        assertTrue(result);
    }

    @Test
    public void testExecuteScriptForCompleteInvalidFormWithAttributes() {
        Map<Attribute, String> attributes = createCompleteAttributeMap();
        ValidateFormAPI api = new ValidateFormVisitorAPI("form { CampoA = 123 -> [CampoC > 3 -> [5 > 3]][isEmpty(CampoB)]; str(CampoB) <> \"abc\" -> [isEmpty(CampoC)]; str(CampoB) = \"abc\" -> [match(CampoA, \"[0-9]+\") AND isNotEmpty(CampoC)]; CampoA > 100 -> [length(CampoB) > 3 AND isEmpty(CampoC)]; }", attributes);
        boolean result = api.executeScript();

        assertFalse(result); // CampoB não está vazio; CampoC está vazio; Tamanho da resposta do CampoB não é maior que 3
    }

    private Map<Attribute, String> createSimpleAttributeMap() {
        Map<Attribute, String> attributes = new HashMap<>();
        attributes.put(Attribute.create("CampoA", "desc", "Campo A", "NUMBER", ".*"), "123");
        return attributes;
    }

    private Map<Attribute, String> createCompleteAttributeMap() {
        Map<Attribute, String> attributes = new HashMap<>();
        attributes.put(Attribute.create("CampoA", "descA", "Campo A", "NUMBER", "[0-9]+"), "123");
        attributes.put(Attribute.create("CampoB", "descB", "Campo B", "TEXT", ".*"), "abc");
        attributes.put(Attribute.create("CampoC", "descC", "Campo C", "NUMBER", "[0-9]+"), "");
        return attributes;
    }

}
