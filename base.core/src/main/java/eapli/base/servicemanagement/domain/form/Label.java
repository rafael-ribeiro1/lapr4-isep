package eapli.base.servicemanagement.domain.form;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
class Label implements ValueObject {
    private final String label;

    public Label(String label) {
        if(label==null|| label.isBlank ())
            throw new IllegalArgumentException ("Label cannot be null or empty");
        this.label = label;
    }

    protected Label() {
        //ORM
        this.label=null;
    }

    @Override
    public String toString() {
        return label;
    }
}
