package eapli.base.teammanagement.domain;


import eapli.base.common.Cor;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class TipoEquipa implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Embedded
    private Cor cor;

    @Embedded
    @Column(unique = true)
    private CodigoUnico codigoUnico;

    @Embedded
    private Description descricao;

    public TipoEquipa(Cor cor, CodigoUnico codigoUnico, Description descricao) {
        Preconditions.noneNull(descricao, codigoUnico, cor);
        this.cor = cor;
        this.codigoUnico = codigoUnico;
        this.descricao = descricao;
    }

    protected TipoEquipa() {
        //ORM
    }

    public CodigoUnico codigoUnico() {
        return codigoUnico;
    }

    public Description descricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
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
        return String.format("%-15s %-40s", codigoUnico, descricao);
    }
}
