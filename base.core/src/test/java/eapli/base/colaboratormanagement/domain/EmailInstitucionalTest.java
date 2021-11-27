package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class EmailInstitucionalTest {


    private static final String INVALID_EMAIL = "CANDIDO11231.COM";

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        EmailInstitucional emailInstitucional = new EmailInstitucional(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhiteSpaceIsNotAllowed() {
        EmailInstitucional emailInstitucional = new EmailInstitucional("        ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        EmailInstitucional emailInstitucional = new EmailInstitucional("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureIncorrectNotAllowed() {
        EmailInstitucional emailInstitucional = new EmailInstitucional(INVALID_EMAIL);
    }

}