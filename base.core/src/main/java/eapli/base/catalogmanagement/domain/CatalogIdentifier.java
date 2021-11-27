package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Embeddable
public class CatalogIdentifier implements ValueObject, Comparable<CatalogIdentifier> {


    private Long id;

    protected CatalogIdentifier() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogIdentifier that = (CatalogIdentifier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }

    @Override
    public int compareTo(CatalogIdentifier o) {
        return Long.compare(this.id, o.id);
    }
}
