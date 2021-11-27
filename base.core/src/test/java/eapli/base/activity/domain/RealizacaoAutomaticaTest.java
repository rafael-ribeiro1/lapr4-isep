package eapli.base.activity.domain;

import org.junit.Test;

public class RealizacaoAutomaticaTest {

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        System.out.println("-----Test 1 (null)-----\n");
        RealizacaoAutomatica r = new RealizacaoAutomatica(null, null, null);
        RealizacaoAutomatica r1 = new RealizacaoAutomatica(null);
    }


}