package eapli.base.criticidade.domain;

import eapli.base.colaboratormanagement.domain.NomeCurto;
import eapli.base.criticidademanagement.domain.EtiquetaCriticidade;
import org.junit.Test;

public class EtiquetaCriticidadeTest {
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        EtiquetaCriticidade local = new EtiquetaCriticidade(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        EtiquetaCriticidade local = new EtiquetaCriticidade("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        EtiquetaCriticidade local = new EtiquetaCriticidade("    ");
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureSizeAllowed() {
        EtiquetaCriticidade local = new EtiquetaCriticidade(" aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa   ");



}}
