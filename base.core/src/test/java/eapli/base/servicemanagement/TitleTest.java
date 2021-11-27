package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.Title;
import org.junit.Test;

import static org.junit.Assert.*;

public class TitleTest {
    @Test
    public void createValidTitle(){
        String title="title";
        Title t=new Title (title);
        assertEquals ( t.toString (),title );
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateTitleWithNullString(){
        new Title (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateTitleWithEmptyString1(){
        new Title ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateTitleWithEmptyString2(){
        new Title ("  ");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateDescriptionWithLongerTextThanAccepted(){
        new Title ( "a".repeat ( Title.TITLE_MAX_LENGTH + 1 ) );
    }
}