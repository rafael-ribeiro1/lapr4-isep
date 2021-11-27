package eapli.base.servicemanagement.domain.form;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name implements ValueObject{
    private final String name;

    public Name(String name) {
        if(name==null|| name.isBlank () || !name.matches ( "[A-Za-z][A-Za-z0-9 ]*" ))
            throw new IllegalArgumentException ("Name cannot be null or empty");
        this.name = name;
    }

    protected Name() {
        //ORM
        name=null;
    }

    public boolean hasSameName(String other){
        if(other==null)
            return false;
        return this.name.trim ().equalsIgnoreCase ( other.trim () );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
