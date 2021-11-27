package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;

import java.util.HashMap;
import java.util.Map;

public abstract class ValidateFormAPI {

    protected String script;

    protected Map<Attribute,String> attributes;

    public ValidateFormAPI(String script, Map<Attribute, String> attributes) {
        this.script = script;
        this.attributes = attributes != null
            ? new HashMap<>(attributes)
            : new HashMap<>();
    }

    public ValidateFormAPI(String script) {
        this.script = script;
        this.attributes = new HashMap<>();
    }

    public abstract boolean validateScript();

    public abstract boolean executeScript() throws IllegalArgumentException;

}
