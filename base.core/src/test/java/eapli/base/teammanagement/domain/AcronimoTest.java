package eapli.base.teammanagement.domain;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class AcronimoTest {

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidAcronimo01(){
        System.out.println("-----Test 1 (white space)-----\n");
        Acronimo acronimo = new Acronimo(" ");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidAcronimo02(){
        System.out.println("-----Test 2 (empty)-----\n");
        Acronimo acronimo = new Acronimo("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidAcronimo03(){
        System.out.println("-----Test 3 (null)-----\n");
        Acronimo acronimo = new Acronimo(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidAcronimo04(){
        System.out.println("-----Test 4 (length > 10)-----\n");
        Acronimo acronimo = new Acronimo("AbCdEfG1HIJKLMnS");
    }

    @Test
    public void testValueOf01() {
        System.out.println("-----Test 1 method valueOf-----\n");
        Acronimo acronimo = new Acronimo("ACRNTTEST");
        Acronimo acr = Acronimo.valueOf("ACRNTTEST");
        assertEquals(acr, acronimo);
    }

    @Test
    public void testValueOf02() {
        System.out.println("-----Test 2 method valueOf-----\n");
        Acronimo acronimo = new Acronimo("ACRNTTEST");
        Acronimo acr = Acronimo.valueOf("aaBBccc");
        assertNotEquals(acr, acronimo);
    }

    @Test
    public void testEquals01() {
        System.out.println("-----Test 1 method equals-----\n");
        Acronimo a1 = new Acronimo("ACRNTTEST");
        Acronimo a2 = new Acronimo("ACRNTTEST");
        assertEquals(a1, a2);
    }

    @Test
    public void testEquals02() {
        System.out.println("-----Test 2 method equals-----\n");
        Acronimo a1 = new Acronimo("ACRNTTEST");
        Acronimo a2 = new Acronimo("VacBcO");
        assertNotEquals(a1, a2);
    }

    @Test
    public void testHashCode() {
        System.out.println("-----Test method hashCode-----\n");
        Acronimo acr = new Acronimo("ABCDEFAJno");
        assertEquals(Objects.hashCode(acr), acr.hashCode());
    }

}