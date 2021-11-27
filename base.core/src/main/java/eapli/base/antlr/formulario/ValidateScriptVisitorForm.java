package eapli.base.antlr.formulario;

import eapli.base.servicemanagement.domain.form.Attribute;

import java.util.HashMap;
import java.util.Map;

public class ValidateScriptVisitorForm extends ValidacaoFormBaseVisitor<Boolean> {

    private Map<Attribute,String> attributes;
    private boolean valid;

    public ValidateScriptVisitorForm(Map<Attribute,String> attributes) {
        this.attributes = new HashMap<>(attributes);
        this.valid = true;
    }

    public boolean isValid() {
        return this.valid;
    }

    @Override
    public Boolean visitAttribute(ValidacaoFormParser.AttributeContext ctx) {
        String name = ctx.name.getText();
        boolean found = false;
        for (Attribute attribute : attributes.keySet()) {
            if (name.equals(attribute.name()))
                found = true;
        }
        if (!found)
            valid = false;
        return super.visitAttribute(ctx);
    }
}
