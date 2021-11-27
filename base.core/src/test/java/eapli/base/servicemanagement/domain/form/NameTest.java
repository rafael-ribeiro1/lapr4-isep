package eapli.base.servicemanagement.domain.form;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class NameTest {
    @Test
    public void successfullyCreateName(){
        String name="name";
        Name result=new Name (name);
        Assertions.assertNotNull ( result );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNullName(){
        new Name (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateEmptyName(){
        new Name ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateBlankName(){
        new Name ("  ");
    }

    @Test
    public void NameClassHasSameNameAsString(){
        String name="name";
        Name n=new Name (name.toUpperCase ());
        Assertions.assertTrue ( n.hasSameName ( name ) );
    }

    @Test
    public void NameClassHasSameNameAsStringWithSpace(){
        String name="name";
        Name n=new Name (name);
        Assertions.assertTrue ( n.hasSameName ( name+" " ) );
    }

    @Test
    public void checkEqualNamesWithNullString(){
        String name="name";
        Name n=new Name (name);
        Assertions.assertFalse ( n.hasSameName ( null ) );
    }

    @Test
    public void nameClassHasDifferentName(){
        String name="name";
        Name n=new Name (name);
        Assertions.assertFalse ( n.hasSameName ( name+"1" ) );
    }
}