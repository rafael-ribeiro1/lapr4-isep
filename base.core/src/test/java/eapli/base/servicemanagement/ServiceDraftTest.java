package eapli.base.servicemanagement;

import eapli.base.servicemanagement.domain.ServiceDraft;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceDraftTest {
    private ServiceDraft draft;

    @Before
    public void setUp() throws Exception {
        draft=new ServiceDraft ();
    }

    @Test
    public void createServiceDraftWithNecessaryInfo(){
        String title="title";
        String id="id1";
        ServiceDraft s=draft.withTitle ( title ).withServiceId ( id );
        assertNotNull (s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceDraftWithoutID(){
        String title="title";
        draft.withTitle ( title ).build ();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateServiceDraftWithoutTitle(){
        String id="id1";
        draft.withServiceId ( id ).build ();
    }
    @Test
    public void createServiceDraftWithForm(){
        String id="id1";
        String title="title";
        draft.withServiceId ( id ).withTitle ( title );
    }


}