package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Title implements ValueObject {
    private final String title;
    public static final int TITLE_MAX_LENGTH=50;

    public Title(String title) {
        if(title==null||title.isBlank ()){
            throw new IllegalArgumentException ("Title cannot be null or empty");
        }
        if (title.length() > TITLE_MAX_LENGTH)
            throw new IllegalArgumentException("Title max length is "+TITLE_MAX_LENGTH +" characters");
        this.title = title;
    }

    protected Title() {
        //ORM
        title=null;
    }

    @Override
    public String toString() {
        return title;
    }
}
