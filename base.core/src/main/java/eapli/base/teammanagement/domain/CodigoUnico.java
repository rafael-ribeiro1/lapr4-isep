package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoUnico implements ValueObject{

    private final String codigo;
    private static final int MAX_LENGTH = 15;

    public CodigoUnico(final String codigo) {
        if (StringPredicates.isNullOrEmpty(codigo) || StringPredicates.isNullOrWhiteSpace(codigo))
            throw new IllegalArgumentException("Unique Code can't be null nor empty.");
        if (codigo.length() > MAX_LENGTH )
            throw new IllegalArgumentException("Unique Code max length is 15 characters");
        if (!StringPredicates.containsDigit(codigo) || !StringPredicates.containsAlpha(codigo))
            throw new IllegalArgumentException("Unique Code has to be alphanumeric");
        this.codigo = codigo;
    }

    protected CodigoUnico() {
        //ORM
        codigo = null;
    }

    public static CodigoUnico valueOf(final String name) {
        return new CodigoUnico(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoUnico)) return false;
        CodigoUnico that = (CodigoUnico) o;
        return codigo.equalsIgnoreCase(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("%s", codigo);
    }
}
