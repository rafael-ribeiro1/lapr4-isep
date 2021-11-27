package eapli.base.catalogmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TitleTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Title title = new Title(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        Title title = new Title("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsLess50() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 55; i++) builder.append("a");
        Title title = new Title(builder.toString());
    }

    @Test
    public void ensureTwoTitlesAreEqual() {
        Title title1 = new Title("abc");
        Title title2 = new Title("abc");
        assertEquals(title1, title2);
    }

    @Test
    public void ensureTwoTitlesAreDifferent() {
        Title title1 = new Title("abc");
        Title title2 = new Title("def");
        assertNotEquals(title1, title2);
    }

}