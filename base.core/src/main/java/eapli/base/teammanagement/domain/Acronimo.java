package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Acronimo implements ValueObject {
        private final String acr;

        private static final int MAX_LENGTH = 10;

        public Acronimo(final String acr) {
                if (StringPredicates.isNullOrEmpty(acr) || StringPredicates.isNullOrWhiteSpace(acr))
                        throw new IllegalArgumentException("Acronym can't be null nor empty.");
                if (acr.length() > MAX_LENGTH )
                        throw new IllegalArgumentException("Acronym max length is 10 characters");
                this.acr = acr;
        }

        protected Acronimo() {
                //ORM
                acr = null;
        }

        public static Acronimo valueOf(final String name) {
                return new Acronimo(name);
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Acronimo)) return false;
                Acronimo acronimo = (Acronimo) o;
                return acr.equals(acronimo.acr);
        }

        @Override
        public int hashCode() {
                return Objects.hash(acr);
        }

        @Override
        public String toString() {
                return String.format("%s", acr);
        }
}
