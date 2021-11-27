package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.domain.ServiceDraftBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ServiceBuilderTest {
    private ServiceDraftBuilder b;
    private String title;
    private String id;

    @Before
    public void setUp() throws Exception {
        b=new ServiceDraftBuilder ();
        title="title";
        id="id1";
    }

    @Test
    public void createServiceDraftWithNecessaryInfo(){
        ServiceDraft s=b.withTitle ( title ).withServiceId ( id ).build ();
        assertNotNull (s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceDraftWithoutID(){
        b.withTitle ( title ).build ();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceDraftWithoutTitle(){
        b.withServiceId ( id ).build ();
    }

    @Test
    public void addNameToFilloutFormToServiceDraft(){
        String formName="form";
        b.withTitle ( title )
                .withServiceId ( id )
                .withFilloutFormName ( formName );
    }
}