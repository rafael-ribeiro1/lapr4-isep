package eapli.base.teammanagement.domain;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Equipa implements AggregateRoot<Long> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true)
    private Acronimo acronimo; //Regra: Máximo 10 Caracteres

    @Embedded
    @Column(unique = true)
    private CodigoUnico codigoUnico; //Regra: Máximo 15 caracteres alfanuméricos

    @Embedded
    private Designation designacao;

    //cascade = CascadeType.NONE pois TipoEquipa pertence a outro agregado
    @ManyToOne(optional = false)
    private TipoEquipa tipoEquipa;

    @ManyToMany
    @JoinTable(
            name = "equipa_colaboradores",
            joinColumns = @JoinColumn(name = "equipa_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private final Set<Colaborador> colaboradores = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_responsaveis",
            joinColumns = @JoinColumn(name = "equipa_id"),
            inverseJoinColumns = @JoinColumn(name = "responsaveis_id")
    )
    private final Set<Colaborador> responsaveis = new HashSet<>();

    public Equipa(Acronimo acronimo, CodigoUnico codigoUnico, Designation designacao, TipoEquipa tipoEquipa,
                  Set<Colaborador> colaboradores,  Set<Colaborador> responsaveis) {
        Preconditions.noneNull(designacao, tipoEquipa, colaboradores, responsaveis);
        Preconditions.nonEmpty(colaboradores);
        Preconditions.nonEmpty(responsaveis);
        if (designacao.isEmpty())
            throw new IllegalArgumentException("At least one parameter is empty");

        this.acronimo = acronimo;
        this.codigoUnico = codigoUnico;
        this.designacao = designacao;
        this.tipoEquipa = tipoEquipa;
        this.colaboradores.addAll(colaboradores);
        this.responsaveis.addAll(responsaveis);
    }

    protected Equipa() {
        //ORM
    }

    public boolean adicionarColaboradorEquipa(Colaborador colaborador){
        if(this.colaboradores.contains(colaborador)) return  false;
        return this.colaboradores.add(colaborador);
    }

    public boolean containsCollaborator(Colaborador colaborador) {
        for (Colaborador resp : responsaveis) {
            if (resp.sameAs(colaborador))
                return true;
        }
        for (Colaborador colab : colaboradores) {
            if (colab.sameAs(colaborador))
                return true;
        }
        return false;
    }

    public TipoEquipa tipoEquipa() {
        return tipoEquipa;
    }

    public CodigoUnico codigoUnico() {
        return codigoUnico;
    }

    public Set<Colaborador> colaboradores() {
        return colaboradores;
    }

    public Designation designacao() {
        return designacao;
    }

    public Set<Colaborador> responsaveis() {
        return responsaveis;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this,o);
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
        return String.format("(%s) %s - %s", codigoUnico, acronimo, designacao);
    }

    public boolean removerColaboradorEquipa(Colaborador c) {
        if (!this.colaboradores.contains(c)) return false;
        return colaboradores.remove(c);
    }
}
