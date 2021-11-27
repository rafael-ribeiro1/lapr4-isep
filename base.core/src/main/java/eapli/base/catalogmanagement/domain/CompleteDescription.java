package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CompleteDescription implements ValueObject {

    private static final int MAX_LENGTH = 100;

    private final String completeDesc;

    public CompleteDescription(final String completeDesc) {
        if (StringPredicates.isNullOrEmpty(completeDesc))
            throw new IllegalArgumentException("Title should not be null nor empty");
        if (completeDesc.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Title max length is 100 characters");

        this.completeDesc = completeDesc;
    }

    protected CompleteDescription() {
        this.completeDesc = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompleteDescription that = (CompleteDescription) o;
        return Objects.equals(completeDesc, that.completeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completeDesc);
    }

    @Override
    public String toString() {
        return completeDesc;
    }
}
