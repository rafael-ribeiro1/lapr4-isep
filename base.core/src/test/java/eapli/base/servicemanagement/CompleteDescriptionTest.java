package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.CompleteDescription;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompleteDescriptionTest {
    @Test
    public void createValidDescription(){
        String description="description1";
        CompleteDescription d=new CompleteDescription (description);
        assertEquals ( d.toString (),description );
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithNullString(){
        String description=null;
        new CompleteDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithEmptyString1(){
        String description="";
        new CompleteDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithEmptyString2(){
        String description="  ";
        new CompleteDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithLongerTextThanAccepted(){
        new CompleteDescription ( "a".repeat ( CompleteDescription.COMPLETE_DESCRIPTION_LENGTH + 1 ) );
    }
}