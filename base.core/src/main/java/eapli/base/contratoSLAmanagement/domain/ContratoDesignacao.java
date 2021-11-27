package eapli.base.contratoSLAmanagement.domain;

import eapli.base.catalogmanagement.domain.BriefDescription;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ContratoDesignacao implements ValueObject {

    private final String designacao;

    protected ContratoDesignacao(){this.designacao=null;}

    public ContratoDesignacao(final String designacao){
        if (StringPredicates.isNullOrEmpty(designacao))
            throw new IllegalArgumentException("Designation should not be null nor empty");
        if (designacao.length() > 25)
            throw new IllegalArgumentException("Designation max length is 25 characters");

        this.designacao = designacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoDesignacao a = (ContratoDesignacao) o;
        return Objects.equals(this.designacao, a.designacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designacao);
    }

    @Override
    public String toString() {
        return designacao;
    }
}
