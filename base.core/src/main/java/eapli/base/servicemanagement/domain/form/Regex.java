package eapli.base.servicemanagement.domain.form;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
class Regex implements ValueObject {
    private final String regex;

    public Regex(String regex) {
        if(regex==null|| regex.isBlank ())
            throw new IllegalArgumentException ("Regular expression cannot be null or empty");
        this.regex = regex;
    }

    protected Regex() {
        this.regex=null;
    }

    @Override
    public String toString() {
        return regex;
    }
}
