package eapli.base.catalogmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BriefDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        BriefDescription briefDesc = new BriefDescription(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        BriefDescription briefDesc = new BriefDescription("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLengthIsLess40() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 45; i++) builder.append("a");
        BriefDescription briefDesc = new BriefDescription(builder.toString());
    }

    @Test
    public void ensureTwoTitlesAreEqual() {
        BriefDescription briefDesc1 = new BriefDescription("abc");
        BriefDescription briefDesc2 = new BriefDescription("abc");
        assertEquals(briefDesc1, briefDesc2);
    }

    @Test
    public void ensureTwoTitlesAreDifferent() {
        BriefDescription briefDesc1 = new BriefDescription("abc");
        BriefDescription briefDesc2 = new BriefDescription("def");
        assertNotEquals(briefDesc1, briefDesc2);
    }

}