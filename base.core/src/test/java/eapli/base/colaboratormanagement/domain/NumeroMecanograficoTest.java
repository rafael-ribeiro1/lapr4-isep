package eapli.base.colaboratormanagement.domain;

import org.junit.Test;

public class NumeroMecanograficoTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        NumeroMecanografico local = new NumeroMecanografico(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhiteSpaceIsNotAllowed() {
        NumeroMecanografico local = new NumeroMecanografico("  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        NumeroMecanografico local = new NumeroMecanografico("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureIncorrectNotAllowed() {
        NumeroMecanografico local = new NumeroMecanografico(" dasdsadasdsadada");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurenNotLetter() {
        NumeroMecanografico local = new NumeroMecanografico(" dasdsadasdsadada");
    }

}
