package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;

@Embeddable
public class Keyword implements ValueObject {

    private final String keyword;

    public Keyword(String keyword) {
        if(keyword==null||keyword.isBlank ()){
            throw new IllegalArgumentException ("Keyword cannot be null or empty");
        }
        this.keyword = keyword;
    }

    public Keyword() {
        //ORM Only
        keyword=null;
    }

    @Override
    public String toString() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Keyword keyword1 = (Keyword) o;
        return keyword.trim ().equalsIgnoreCase ( keyword1.keyword.trim () );
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder ( 17, 37 ).append ( keyword ).toHashCode ();
    }
}
