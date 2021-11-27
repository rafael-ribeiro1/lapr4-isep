package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class BriefDescription implements ValueObject  {
    private final String briefText;

    public static final int BRIEF_DESCRIPTION_LENGTH =40;

    public BriefDescription(final String briefText)  throws IllegalArgumentException {
        if(briefText ==null|| briefText.isBlank () || briefText.length ()> BRIEF_DESCRIPTION_LENGTH){
            throw new IllegalArgumentException ("Description cannot be null, empty and cannot exceed "+ BRIEF_DESCRIPTION_LENGTH +" characters.");
        }
        this.briefText = briefText;
    }

    protected BriefDescription() {
        //ORM
        briefText =null;
    }

    @Override
    public String toString() {
        return briefText;
    }
}
