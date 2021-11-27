package eapli.base.activity.domain;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataLimiteTest {

    private Calendar date = new GregorianCalendar();

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed(){
        System.out.println("-----Test 1 (null)-----\n");
        DataLimite d = new DataLimite(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureTodayOrBeforeIsNotAllowed(){
        System.out.println("-----Test 2 (date -> must be after today)-----\n");
        date.set(2021, Calendar.FEBRUARY,10);
        DataLimite d = new DataLimite(date);
    }
}