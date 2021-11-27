
package eapli.base.servicemanagement.domain.form;

import eapli.base.utils.ANSI;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.Objects;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Entity
public class Attribute implements ValueObject, DTOable<AttributeDTO> {
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name name;
    @Embedded
    private Description description;
    @Embedded
    private Label label;
    @Enumerated(EnumType.STRING)
    private DataType dataType;
    @Embedded
    private Regex regex;

    private Attribute(Name name, Description description, Label label, DataType dataType, Regex regex) throws IllegalArgumentException{
        this.name = name;
        this.description = description;
        this.label = label;
        this.dataType = dataType;
        this.regex = regex;
    }

    protected Attribute() {/*ORM*/}

    public static Attribute create(final String name,final  String description, final String label,
                                   final String dataType,final String regex) throws IllegalArgumentException, NullPointerException{
        Name n=new Name (name);
        Description d=Description.valueOf (description);
        Label e=new Label (label);
        Regex r=new Regex (regex);
        DataType t=DataType.valueOf ( dataType.strip ().toUpperCase () );
        //DataType t= DataType.valueOf (dataType.trim ().toUpperCase ());
        return new Attribute (n,d,e,t,r);
    }


    boolean hasSameVariableName(String otherName) {
        return name.hasSameName (otherName);
    }

    public String name() {
        return this.name.toString();
    }

    public String label() {
        return this.label.toString();
    }

    public String regex() {
        return this.regex.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return attribute.name.toString().equals(name.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash( name);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name=" + name +
                ", description=" + description +
                ", label=" + label +
                ", dataType=" + dataType +
                ", regex=" + regex +
                '}';
    }

    @Override
    public AttributeDTO toDTO() {
        return new AttributeDTO (name.toString (),description.toString (),label.toString (),
                dataType.toString (),regex.toString ());
    }
}
