package eapli.base.activity.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtividadeAprovacaoTest {

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed01() {
        System.out.println("-----Test 1 (null)-----\n");
        AtividadeAprovacao a = new AtividadeAprovacao(null, null);
        AtividadeAprovacao a1 = new AtividadeAprovacao( null, null, null);
    }
}