package eapli.base.common;

import eapli.base.catalogmanagement.domain.Title;
import org.junit.Test;

import static org.junit.Assert.*;

public class IconTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Icon icon = new Icon(null);
    }

    @Test
    public void ensureTwoTitlesAreEqual() {
        Icon icon1 = new Icon(new byte[] {1,2,3});
        Icon icon2 = new Icon(new byte[] {1,2,3});
        assertEquals(icon1, icon2);
    }

    @Test
    public void ensureTwoTitlesAreDifferent() {
        Icon icon1 = new Icon(new byte[] {1,2,3});
        Icon icon2 = new Icon(new byte[] {1});
        assertNotEquals(icon1, icon2);
    }

}