package eapli.base.servicemanagement.domain.form;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormDraftTest {
    private FormDraft draft;
    private String name;
    private String id;
    private Attribute attribute;

    @Before
    public void setUp() throws Exception {
        this.draft=new FormDraft ();
        this.name="formDraft";
        this.id="formId1";
        //this.attribute=Attribute.create ( "at1","desc","label",DataType.valueOf ( "BOOLEAN" ) )
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateFormWithNoFieldsDefined(){
        draft.build ();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateFormWithNoNameDefined(){
        draft.build ();
    }
}