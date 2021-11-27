package eapli.base.criticidade.domain;

import eapli.base.criticidademanagement.domain.EtiquetaCriticidade;
import eapli.base.criticidademanagement.domain.ObjetivosCriticidade;
import org.junit.Test;

public class ObjetivosCriticidadeTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureCorrectValuesIsNotAllowed() {
        ObjetivosCriticidade ob = new ObjetivosCriticidade(-1,-1,-1,-1);
    }


}
