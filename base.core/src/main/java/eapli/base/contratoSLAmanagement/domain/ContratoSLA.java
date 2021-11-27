package eapli.base.contratoSLAmanagement.domain;

import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ContratoSLA implements AggregateRoot<Long> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private ContratoDesignacao designacao;

    @OneToMany
    private Set<Criticidade> listaCriticidades;

    protected ContratoSLA(){

    }

    public ContratoSLA(ContratoDesignacao designacao , Set<Criticidade> listaCriticidades){
        Preconditions.noneNull(designacao,listaCriticidades);
        if(listaCriticidades.isEmpty()) throw new IllegalArgumentException("Criticaly list cannot be empty!");
        this.designacao=designacao;
        this.listaCriticidades=listaCriticidades;
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this,other);
    }

    @Override
    public String toString() {
        return designacao.toString();
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
