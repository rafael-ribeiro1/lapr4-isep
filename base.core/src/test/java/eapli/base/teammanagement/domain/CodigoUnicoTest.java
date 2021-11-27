package eapli.base.teammanagement.domain;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class CodigoUnicoTest {

    @Test(expected=IllegalArgumentException.class)
    public void ensureWhiteSpaceParameterIsNotAllowed(){
        System.out.println("-----Test 1 (white space)-----\n");
        CodigoUnico acronimo = new CodigoUnico(" ");
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureEmptyParameterIsNotAllowed(){
        System.out.println("-----Test 2 (empty)-----\n");
        CodigoUnico acronimo = new CodigoUnico("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullParameterIsNotAllowed(){
        System.out.println("-----Test 3 (null)-----\n");
        CodigoUnico acronimo = new CodigoUnico(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureAboveMaxLengthParameterIsNotAllowed(){
        System.out.println("-----Test 4 (length > 15)-----\n");
        CodigoUnico acronimo = new CodigoUnico("Ab111CdEfG1HIJ12345KLMnS");
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureOnlyNumericParameterIsNotAllowed(){
        System.out.println("-----Test 5 (only numeric)-----\n");
        CodigoUnico acronimo = new CodigoUnico("123456789123456");
    }

    @Test(expected=IllegalArgumentException.class)
    public void ensureOnlyAlphaParameterIsNotAllowed(){
        System.out.println("-----Test 6 (only alpha)-----\n");
        CodigoUnico acronimo = new CodigoUnico("AbCDqWRtHjKlNMB");
    }


    @Test
    public void valueOf01() {
        System.out.println("-----Test 1 method valueOf-----\n");
        CodigoUnico cod = new CodigoUnico("T1E2S3t4e5t6E7s");
        CodigoUnico c = CodigoUnico.valueOf("T1E2S3t4e5t6E7s");
        assertEquals(c, cod);
    }

    @Test
    public void valueOf02() {
        System.out.println("-----Test 2 method valueOf-----\n");
        CodigoUnico cod = new CodigoUnico("T1E2S3t4e5t6E7s");
        CodigoUnico c = CodigoUnico.valueOf("T1E2S3t11116E7s");
        assertNotEquals(c, cod);
    }

    @Test
    public void testEquals01() {
        System.out.println("-----Test 1 method equals-----\n");
        CodigoUnico cod1 = new CodigoUnico("T1E2S3t4e5t6E7s");
        CodigoUnico cod2 = new CodigoUnico("T1E2S3t4e5t6E7s");
        assertEquals(cod1, cod2);
    }

    @Test
    public void testEquals02() {
        System.out.println("-----Test 2 method equals-----\n");
        CodigoUnico cod1 = new CodigoUnico("T1E2S3t4e5t6E7s");
        CodigoUnico cod2 = new CodigoUnico("T1E2S3t11116E7s");
        assertNotEquals(cod1, cod2);
    }

    @Test
    public void testHashCode() {
        System.out.println("-----Test method hashCode-----\n");
        CodigoUnico cod = new CodigoUnico("T1E2S3t4e5t6E7s");
        assertEquals(Objects.hashCode(cod), cod.hashCode());
    }
}