package eapli.base.colaboratormanagement.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Funcao implements AggregateRoot<Long> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Designation name;

    protected Funcao() {

    }

    public Funcao(final Designation name) {
        Preconditions.noneNull(name);
        this.name = name;
    }

    public Funcao(final String name) {
        this.name = Designation.valueOf(name);
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
        return name.toString();
    }
}
