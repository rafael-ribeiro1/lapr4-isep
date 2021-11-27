package eapli.base.servicemanagement.domain.form;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class LabelTest {
    @Test
    public void successfullyCreateLabel(){
        String label="label";
        Label result=new Label (label);
        Assertions.assertNotNull ( result );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNullLabel(){
        new Label (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateEmptyLabel(){
        new Label ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateBlankLabel(){
        new Label ("  ");
    }

}