package eapli.base.criticidademanagement.domain;

import eapli.base.common.Cor;
import eapli.framework.domain.model.AggregateRoot;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;

@Entity
public class Criticidade implements AggregateRoot<Long> {
    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Cor corCriticidade;

    @Embedded
    private EtiquetaCriticidade etiquetaCriticidade;

    @Embedded
    private ObjetivosCriticidade objetivosCriticidade;

    @Embedded
    private ValorCriticidade valor;

    public Criticidade(Cor corCriticidade,EtiquetaCriticidade etiquetaCriticidade,ObjetivosCriticidade objetivosCriticidade,ValorCriticidade valor){
        Preconditions.noneNull(corCriticidade,etiquetaCriticidade,objetivosCriticidade,valor);
        this.corCriticidade=corCriticidade;
        this.etiquetaCriticidade=etiquetaCriticidade;
        this.objetivosCriticidade=objetivosCriticidade;
        this.valor=valor;
    }


    protected Criticidade(){

    }

    public Criticidade defineNewObjectives(int temMaxApro, int temAvgAprova, int tempMaxReso, int tempAvgReso){
        Preconditions.noneNull(temMaxApro,temAvgAprova,tempMaxReso,tempAvgReso);
        this.corCriticidade= new Cor(this.corCriticidade.red(),this.corCriticidade.green(),this.corCriticidade.blue());
        this.etiquetaCriticidade= new EtiquetaCriticidade(this.etiquetaCriticidade.etiqueta());
        this.valor = new ValorCriticidade(this.valor.valor());
        this.objetivosCriticidade = new ObjetivosCriticidade(temMaxApro,tempMaxReso,temAvgAprova,tempAvgReso);
        return new Criticidade(corCriticidade,etiquetaCriticidade,objetivosCriticidade,valor);
    }

    public ValorCriticidade valor() {
        return valor;
    }

    public String etiqueta() {
        return this.etiquetaCriticidade.toString();
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }


    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s com valor : %s",etiquetaCriticidade,valor);
    }
}
