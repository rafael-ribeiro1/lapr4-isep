package eapli.base.catalogmanagement.domain;

import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.common.Icon;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Catalog implements AggregateRoot<Long>, DTOable<CatalogDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /*@EmbeddedId
    private CatalogIdentifier id;*/
    @Embedded
    private Title title;
    @Embedded
    private BriefDescription briefDesc;
    @Embedded
    private CompleteDescription completeDesc;
    @Embedded
    private Icon icon;
    @ManyToOne
    private Colaborador resp;
    @ManyToMany
    private List<Equipa> accessAllowed = new ArrayList<>();

    @ManyToOne
    private Criticidade criticidade;

    protected Catalog() {

    }

    public Catalog(final String title, final String briefDesc,
                   final String completeDesc, final byte[] icon,
                   final Colaborador resp, List<Equipa> accessAllowed) {
        if (resp == null || accessAllowed == null)
            throw new IllegalArgumentException("Parametros obrigatórios");

        this.title = new Title(title);
        this.briefDesc = new BriefDescription(briefDesc);
        this.completeDesc = new CompleteDescription(completeDesc);
        this.icon = new Icon(icon);
        this.resp = resp;
        this.accessAllowed = new ArrayList<>(accessAllowed);
    }

    public Catalog(final Title title, final BriefDescription briefDesc,
                   final CompleteDescription completeDesc, final Icon icon,
                   final Colaborador resp, List<Equipa> accessAllowed) {
        if (title == null || briefDesc == null || completeDesc == null ||
                icon == null || resp == null || accessAllowed == null)
            throw new IllegalArgumentException("Parametros obrigatórios");

        this.title = title;
        this.briefDesc = briefDesc;
        this.completeDesc = completeDesc;
        this.icon = icon;
        this.resp = resp;
        this.accessAllowed = new ArrayList<>(accessAllowed);
    }

    public Criticidade criticidade() {
        return criticidade;
    }

    public Colaborador responsavel() {
        return resp;
    }

    public List<Equipa> teamsWithAccess() {
        return new ArrayList<>(accessAllowed);
    }

    public boolean containsSearchTerm(String term) {
        String lower = term.toLowerCase();
        return title.toString().toLowerCase().contains(lower) ||
                briefDesc.toString().toLowerCase().contains(lower) ||
                completeDesc.toString().toLowerCase().contains(lower);
    }

    public boolean verifyAccessColaborator(Colaborador colaborador){
        for(Equipa e : accessAllowed){
            if(e.containsCollaborator(colaborador)) return true;
        }
        return false;
    }


    public void defineCriticidade(Criticidade criticidade) {
        this.criticidade = criticidade;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("(ID %s) %s", this.id.toString(), this.title.toString());
    }

    public String details() {
        return "=== Catalog ================\n"
                + "Identifier = '" + id + "'" +
                ", \n" + "Title = '" + title + "'" +
                ", \n" + "BriefDescription = '" + briefDesc + "'" +
                ", \n" + "CompleteDescription = '" + completeDesc + "'" +
                ", \n" + "ResponsibleCollaborator = '" + resp + "'" +
                ", \n" + "TeamsWithAccess = '" + accessAllowed + "'\n" +
                "============================\n";
    }

    @Override
    public CatalogDTO toDTO() {
        return new CatalogDTO ( this.identity (),this.title.toString (),this.briefDesc.toString (),criticidade );
    }
}
