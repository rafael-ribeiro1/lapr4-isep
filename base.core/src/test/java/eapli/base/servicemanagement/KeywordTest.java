package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.Keyword;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeywordTest {
    @Test
    public void successfullyCreateKeyword(){
        String keyword="keyword1";
        Keyword k=new Keyword (keyword);
        assertEquals ( k.toString (),keyword );
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateKeywordWithNullString(){
        String keyword=null;
        new Keyword (keyword);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateKeywordWithEmptyString1(){
        String keyword="";
        new Keyword (keyword);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateKeywordWithEmptyString2(){
        String keyword="  ";
        new Keyword (keyword);
    }
}