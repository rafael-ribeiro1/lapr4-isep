package eapli.base.criticidade.domain;

import eapli.base.criticidademanagement.domain.ObjetivosCriticidade;
import eapli.base.criticidademanagement.domain.ValorCriticidade;
import org.junit.Test;

public class ValorCriticidadeTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNegativeValueIsNotAllowed() {
        ValorCriticidade ob = new ValorCriticidade(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureHighValuesIsNotAllowed() {
        ValorCriticidade ob = new ValorCriticidade(102);
    }

}
