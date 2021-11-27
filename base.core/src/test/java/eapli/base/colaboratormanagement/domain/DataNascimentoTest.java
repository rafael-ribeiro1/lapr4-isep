package eapli.base.colaboratormanagement.domain;

import eapli.framework.time.util.Calendars;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataNascimentoTest {

    private final Calendar DATA_INVALIDA = new GregorianCalendar();


    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        DataNascimento data = new DataNascimento(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCorrectDateIsNotAllowed() {
        DATA_INVALIDA.set(2040,11,20);
        DataNascimento data = new DataNascimento(DATA_INVALIDA);
    }

    @Test
    public void ensureTwoDatesAreEqual() {

        Calendar data = new GregorianCalendar();
        data.set(2001,11,20);

        DataNascimento data1 = new DataNascimento(data);
        DataNascimento data2 = new DataNascimento(data);
        assertEquals(data1, data2);
    }

    @Test(expected = IllegalArgumentException.class)
    public  void ensureDateInvalid(){
        DATA_INVALIDA.set(2040,11,20);
        DataNascimento data2 = new DataNascimento(DATA_INVALIDA);
    }

    @Test
    public void ensureTwoDatesAreDifferent() {
        Calendar data = new GregorianCalendar();
        data.set(2015,11,20);
        Calendar data2 = new GregorianCalendar();
        data.set(2018,11,20);
        assertNotEquals(data,data2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNotTooYoungToWork() {
        Calendar data = new GregorianCalendar();
        data.set(2015,11,20);
        DataNascimento data2 = new DataNascimento(data);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureNotTooOldToWork() {
        Calendar data = new GregorianCalendar();
        data.set(1910,11,20);
        DataNascimento data2 = new DataNascimento(data);
    }



}
