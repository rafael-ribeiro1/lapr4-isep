package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.BriefDescription;
import org.junit.Test;

import static org.junit.Assert.*;

public class BriefDescriptionTest {
    @Test
    public void createValidDescription(){
        String description="description1";
        BriefDescription d=new BriefDescription (description);
        assertEquals ( d.toString (),description );
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithNullString(){
        String description=null;
        new BriefDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithEmptyString1(){
        String description="";
        new BriefDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithEmptyString2(){
        String description="  ";
        new BriefDescription (description);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithLongerTextThanAccepted(){
        new BriefDescription ( "a".repeat ( BriefDescription.BRIEF_DESCRIPTION_LENGTH + 1 ) );
    }
}