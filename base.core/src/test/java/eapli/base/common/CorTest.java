package eapli.base.common;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class CorTest {


    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        System.out.println("-----Test 1 (Null)-----\n");
        Cor cor = new Cor(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureGreaterThan255IsNotAllowed() {
        System.out.println("-----Test 2 (Greater than 255)-----\n");
        Cor cor = new Cor(256, 257, 258);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLessThan0IsNotAllowed() {
        System.out.println("-----Test 3 (Less than 0)-----\n");
        Cor cor = new Cor(-127, -126, -127);
    }

    @Test
    public void testEquals01() {
        System.out.println("-----Test 1 method equals-----\n");
        Cor c = new Cor(178, 17, 32);
        assertEquals(c, Cor.valueOf(178, 17, 32));
    }

    @Test
    public void testEquals02() {
        System.out.println("-----Test 2 method equals-----\n");
        Cor c = new Cor(178, 17, 32);
        assertNotEquals(c, Cor.valueOf(155, 17, 32));
    }

    @Test
    public void testHashCode() {
        System.out.println("-----Test method hashCode-----\n");
        Cor c = new Cor(178, 17, 32);
        assertEquals(Objects.hash(178,17,32), c.hashCode());
    }
}