package eapli.base.teammanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.Test;

public class TipoEquipaTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyParameterIsNotAllowed() {
        TipoEquipa t = new TipoEquipa(null, CodigoUnico.valueOf(""), Description.valueOf(""));
    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullParameterIsNotAllowed() {
        TipoEquipa t = new TipoEquipa(null,null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhiteSpaceParameterIsNotAllowed() {
        TipoEquipa t = new TipoEquipa(null, CodigoUnico.valueOf(" "), Description.valueOf(" "));
    }
}