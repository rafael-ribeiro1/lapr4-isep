package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BriefDescription implements ValueObject {

    private static final int MAX_LENGTH = 40;

    private final String briefDesc;

    public BriefDescription(final String briefDesc) {
        if (StringPredicates.isNullOrEmpty(briefDesc))
            throw new IllegalArgumentException("Title should not be null nor empty");
        if (briefDesc.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Title max length is 40 characters");

        this.briefDesc = briefDesc;
    }

    protected BriefDescription() {
        this.briefDesc = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BriefDescription that = (BriefDescription) o;
        return Objects.equals(briefDesc, that.briefDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(briefDesc);
    }

    @Override
    public String toString() {
        return briefDesc;
    }
}
