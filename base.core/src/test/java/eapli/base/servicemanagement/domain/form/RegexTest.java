package eapli.base.servicemanagement.domain.form;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class RegexTest {
    @Test
    public void successfullyCreateRegex(){
        String regex="regex";
        Regex result=new Regex (regex);
        Assertions.assertNotNull ( result );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNullRegex(){
        new Regex (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateEmptyRegex(){
        new Regex ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateBlankRegex(){
        new Regex ("  ");
    }
}