package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;


@Embeddable
public class CompleteDescription implements ValueObject {

    private final String completeText;

    public static final int COMPLETE_DESCRIPTION_LENGTH =500;

    public CompleteDescription(final String completeText) throws IllegalArgumentException {
        if(completeText ==null|| completeText.isBlank () || completeText.length ()> COMPLETE_DESCRIPTION_LENGTH){
            throw new IllegalArgumentException ("Description cannot be null, empty and cannot exceed "+ COMPLETE_DESCRIPTION_LENGTH +" characters.");
        }
        this.completeText = completeText;
    }

    protected CompleteDescription() {
        //ORM
        completeText =null;
    }

    @Override
    public String toString() {
        return completeText;
    }
}
