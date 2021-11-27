package eapli.base.servicemanagement.domain.form;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ServiceLevelIdTest {
    @Test
    public void successfullyCreateServiceLevelId(){
        String id="id1";
        ServiceLevelId result=new ServiceLevelId (id);
        Assertions.assertNotNull ( result );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNullServiceLevelId(){
        new ServiceLevelId (null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateEmptyServiceLevelId(){
        new ServiceLevelId ("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void failToCreateBlankServiceLevelId(){
        new ServiceLevelId ("  ");
    }

}