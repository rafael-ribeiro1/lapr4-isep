package eapli.base.teammanagement.domain;

import eapli.base.colaboratormanagement.domain.*;
import eapli.base.common.Cor;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.*;

public class EquipaTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullParameterIsNotAllowed(){
        Equipa e = new Equipa(null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyParameterIsNotAllowed(){
        Equipa e = new Equipa(Acronimo.valueOf(""), CodigoUnico.valueOf(""), Designation.valueOf(""), new TipoEquipa(null,
                new CodigoUnico(""), Description.valueOf("")),new HashSet<>(), new HashSet<>());
    }
}