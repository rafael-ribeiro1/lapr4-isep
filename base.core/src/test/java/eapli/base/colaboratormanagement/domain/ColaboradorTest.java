package eapli.base.colaboratormanagement.domain;


import org.junit.Test;



public class ColaboradorTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullParameterIsNotAllowed() {
        Colaborador colaborador = new Colaborador(null, null, null, null, null, null,null,null,null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullValueObjectslIsNotAllowed() {
        Colaborador colaborador = new Colaborador(null, null, null, null, null, null,null,null,new Colaborador(),new Funcao("ads"));
    }


}
