package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class NumeroContactoTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        NumeroContacto local = new NumeroContacto(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        NumeroContacto local = new NumeroContacto("     ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        NumeroContacto local = new NumeroContacto("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCorrectLengthAllowed() {
        NumeroContacto local = new NumeroContacto("aadsa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurenNotLetter() {
        NumeroContacto local = new NumeroContacto("aadsaaaaaa");
    }

}
