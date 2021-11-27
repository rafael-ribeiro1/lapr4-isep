package eapli.base.catalogmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Title implements ValueObject {

    private static final int MAX_LENGTH = 50;

    private final String title;

    public Title(final String title) {
        if (StringPredicates.isNullOrEmpty(title))
            throw new IllegalArgumentException("Title should not be null nor empty");
        if (title.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Title max length is 50 characters");

        this.title = title;
    }

    protected Title() {
        this.title = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
