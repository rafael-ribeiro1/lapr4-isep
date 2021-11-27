package eapli.base.catalogmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompleteDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        CompleteDescription desc = new CompleteDescription(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        CompleteDescription desc = new CompleteDescription("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsLess100() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 105; i++) builder.append("a");
        CompleteDescription desc = new CompleteDescription(builder.toString());
    }

    @Test
    public void ensureTwoTitlesAreEqual() {
        CompleteDescription desc1 = new CompleteDescription("abc");
        CompleteDescription desc2 = new CompleteDescription("abc");
        assertEquals(desc1, desc2);
    }

    @Test
    public void ensureTwoTitlesAreDifferent() {
        CompleteDescription desc1 = new CompleteDescription("abc");
        CompleteDescription desc2 = new CompleteDescription("a");
        assertNotEquals(desc1, desc2);
    }

}