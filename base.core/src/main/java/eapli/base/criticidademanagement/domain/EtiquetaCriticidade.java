package eapli.base.criticidademanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class EtiquetaCriticidade implements ValueObject {
    private static final long serialVersionUID = 1L;
    private final String etiqueta;

    public EtiquetaCriticidade(final String etiquetaInput){
            if(StringPredicates.isNullOrWhiteSpace(etiquetaInput))
                throw new IllegalArgumentException("Etiqueta should not be null or empty");
            if(etiquetaInput.length()>25)
                throw new IllegalArgumentException("Max length of etiqueta is 25 characters");

            this.etiqueta=etiquetaInput;
    }
    public String etiqueta() {
        return etiqueta;
    }
    protected  EtiquetaCriticidade (){
        etiqueta="";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtiquetaCriticidade that = (EtiquetaCriticidade) o;
        return Objects.equals(this.etiqueta, that.etiqueta);
    }
    @Override
    public int hashCode() {
        return Objects.hash(etiqueta);
    }

    @Override
    public String toString() {
        return etiqueta;
    }

}
