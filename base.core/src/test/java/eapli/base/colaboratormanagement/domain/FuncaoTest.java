package eapli.base.colaboratormanagement.domain;

import eapli.framework.general.domain.model.Designation;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuncaoTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNotNull() {
        final Funcao funcao = new Funcao((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNotEmpty() {
        final Funcao funcao = new Funcao("");
    }

}