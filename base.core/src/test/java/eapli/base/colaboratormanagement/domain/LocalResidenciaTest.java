package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class LocalResidenciaTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        LocalResidencia local = new LocalResidencia(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhiteSpaceIsNotAllowed() {
        LocalResidencia local = new LocalResidencia("     ","      ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        LocalResidencia local = new LocalResidencia("","");
    }

}
