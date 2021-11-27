package eapli.base.servicemanagement.domain.form;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttributeTest {
    private Attribute a;
    private String name;
    private String description;
    private String label;
    private String dataType;
    private String regex;

    @Before
    public void setUp() throws Exception {
        name="name";
        description="desc";
        label="label";
        dataType="BOOLEAN";
        regex="true|false";
    }

    @Test
    public void successfullyCreateAttribute(){
        a=Attribute.create ( name,description,label,dataType,regex );
        assertNotNull ( a );
    }

    @Test
    public void successfullyCreateAttributeWithDataType(){
        dataType="BOOLEAN   ";
        a=Attribute.create ( name,description,label,dataType,regex );
        assertNotNull ( a );
        dataType=" BOOLEAN";
        a=Attribute.create ( name,description,label,dataType,regex );
        assertNotNull ( a );
        dataType=" BOOLEAN  ";
        a=Attribute.create ( name,description,label,dataType,regex );
        assertNotNull ( a );
        dataType=" boolean  ";
        a=Attribute.create ( name,description,label,dataType,regex );
        assertNotNull ( a );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithNullName(){
        name=null;
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithEmptyName(){
        name="  ";
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithNullDescription(){
        description=null;
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithEmptyDescription(){
        description="  ";
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithNullLabel(){
        label=null;
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithEmptyLabel(){
        label="  ";
        Attribute.create ( name,description,label,dataType,regex );
    }
    //dataType
    @Test(expected = NullPointerException.class)
    public void failToCreateAttributeWithNullDataType(){
        dataType=null;
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithNullRegex(){
        regex=null;
        Attribute.create ( name,description,label,dataType,regex );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateAttributeWithEmptyRegex(){
        regex="  ";
        Attribute.create ( name,description,label,dataType,regex );
    }
}