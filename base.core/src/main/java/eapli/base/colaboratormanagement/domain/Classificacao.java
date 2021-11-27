package eapli.base.colaboratormanagement.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Classificacao {

    private static final long serialVersionUID = 1L;
    final private double classificacao;

    private final static int LOWEST_CLASSIFICACAO=0;


    public Classificacao (final double classi){
        if(classi<LOWEST_CLASSIFICACAO)
            throw new IllegalArgumentException("Valor invalido de classificao");

        this.classificacao=classi;
    }

    protected Classificacao (){
            classificacao=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classificacao that = (Classificacao) o;
        return this.classificacao==that.classificacao;
    }

    public double valorClassificacao() {
        return classificacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.classificacao);
    }

    @Override
    public String toString() {
        return ((Double) this.classificacao).toString();
    }


}
