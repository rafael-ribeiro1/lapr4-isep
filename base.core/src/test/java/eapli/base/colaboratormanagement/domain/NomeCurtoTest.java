package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class NomeCurtoTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        NomeCurto local = new NomeCurto(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        NomeCurto local = new NomeCurto("","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        NomeCurto local = new NomeCurto("      ","       ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCorrectLengthAllowed() {
        NomeCurto local = new NomeCurto("    dassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss  "," dasassdasdaasddd      ");
    }

}
