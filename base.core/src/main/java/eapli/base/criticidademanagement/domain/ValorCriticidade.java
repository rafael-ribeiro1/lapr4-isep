package eapli.base.criticidademanagement.domain;


import eapli.base.Application;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ValorCriticidade implements ValueObject {
    private static final long serialVersionUID = 1L;
    private final int valor;

    private static final int DEFAULT_MAX_VALUE = Application.settings().getCritMaxValue();

    public ValorCriticidade (final int valor){
        if(valor<0||valor> DEFAULT_MAX_VALUE)
            throw new IllegalArgumentException("Invalid value");
        this.valor=valor;
    }


    protected ValorCriticidade(){
        this.valor=-1;
    }

    public int valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValorCriticidade that = (ValorCriticidade) o;
        return Objects.equals(this.valor, that.valor);
    }
    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return ((Integer)valor).toString();
    }

}
