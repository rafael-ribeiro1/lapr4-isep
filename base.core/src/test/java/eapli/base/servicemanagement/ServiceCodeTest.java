package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.ServiceCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceCodeTest {
    @Test
    public void createValidServiceCode(){
        String code="code1";
        ServiceCode d=new ServiceCode (code);
        assertEquals ( d.toString (),code );
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceCodeWithNullString(){
        new ServiceCode (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceCodeWithEmptyString1(){
        new ServiceCode ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceCodeWithEmptyString2(){
        new ServiceCode ("  ");
    }

}