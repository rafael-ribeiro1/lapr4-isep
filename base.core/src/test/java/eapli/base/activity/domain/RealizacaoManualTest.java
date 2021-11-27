package eapli.base.activity.domain;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.form.Form;
import eapli.base.servicemanagement.domain.form.Name;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class RealizacaoManualTest {

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed01() {
        System.out.println("-----Test 1 (null)-----\n");
        RealizacaoManual r = new RealizacaoManual((Colaborador) null,null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed02() {
        System.out.println("-----Test 2 (null)-----\n");
        RealizacaoManual r = new RealizacaoManual(new ArrayList<>(), null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed01() {
        System.out.println("-----Test 1 (empty)-----\n");
        RealizacaoManual r = new RealizacaoManual(new ArrayList<>(),new Form(new Name("test"), new ArrayList<>()));
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed02() {
        System.out.println("-----Test 2 (empty)-----\n");
        Calendar d = new GregorianCalendar();
        d.set(2023, Calendar.DECEMBER, 12);
        RealizacaoManual r = new RealizacaoManual(EstadoAtividade.valueOf("Pendente"), DataLimite.valueOf(d),
                new ArrayList<>(), new Form(new Name("test"), new ArrayList<>()));
    }
}