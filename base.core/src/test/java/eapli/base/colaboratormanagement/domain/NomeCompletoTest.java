package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class NomeCompletoTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        NomeCompleto local = new NomeCompleto(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        NomeCompleto local = new NomeCompleto("    ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        NomeCompleto local = new NomeCompleto("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCorrectLengthAllowed() {
        NomeCompleto local = new NomeCompleto("adssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
    }

}
