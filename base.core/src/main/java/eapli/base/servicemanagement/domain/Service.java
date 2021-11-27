package eapli.base.servicemanagement.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.common.Icon;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.servicemanagement.domain.form.Form;
import eapli.base.servicemanagement.dto.ServiceDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Service implements AggregateRoot<ServiceCode>, DTOable<ServiceDTO> {
    @Version
    private Long version;
    @EmbeddedId
    private ServiceCode serviceCode;
    @ManyToOne
    private Catalog catalog;
    @Embedded
    private Icon icon;
    @ElementCollection
    private List<Keyword> lKeywords;
    @Embedded
    private BriefDescription briefDescription;
    @Embedded
    private CompleteDescription completeDescription;
    @Enumerated(EnumType.STRING)
    private State state;
    @Embedded
    private Title title;
    @OneToOne(cascade=CascadeType.ALL)
    private Form fillOutForm;
    @OneToOne(cascade=CascadeType.ALL)
    private Form feedbackForm;
    @ManyToOne
    private Criticidade criticality;
    @OneToOne(cascade=CascadeType.ALL)
    private FluxoAtividadesServico activityFlow;

    protected Service() {/*ORM*/}

    public Service(final ServiceCode serviceCode, final Catalog catalog, final Icon icon,
                   final List<Keyword> lKeywords, final BriefDescription briefDescription,
                   final CompleteDescription completeDescription, final State state,
                   final Title title, final Form fillOutForm, final Form feedbackForm,
                   final FluxoAtividadesServico activityFlow,final Criticidade criticality) {
        if(serviceCode==null||catalog ==null||icon==null || lKeywords==null||lKeywords.isEmpty () ||briefDescription==null
                || completeDescription ==null ||title==null|| fillOutForm ==null || activityFlow==null || criticality ==null){
            throw new IllegalArgumentException ("Service still has fields to be defined\n");
        }
        this.serviceCode = serviceCode;
        this.catalog = catalog;
        this.icon=icon;
        this.lKeywords = new LinkedList<> ( lKeywords );
        this.briefDescription = briefDescription;
        this.completeDescription = completeDescription;
        this.state = state;
        this.title = title;
        this.fillOutForm = fillOutForm;
        this.feedbackForm=feedbackForm;
        this.activityFlow=activityFlow;
        this.criticality = criticality;
    }

    public Criticidade getCriticality() {
        return criticality;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual ( this,other );
    }

    @Override
    public ServiceCode identity() {
        return this.serviceCode;
    }

    public Form serviceForm() {
        return this.fillOutForm;
    }

    public String subject() {
        return String.format("(%s) %s", serviceCode, title);
    }

    public State state() {
        return state;
    }

    public void changeState(State s){
        this.state=s;
    }

    @Override
    public String toString() {
        return subject();
    }

    public String details() {
        return "Service{\n"
                + "ServiceCode='" + serviceCode + "'" +
                ", \n" + "Title='" + title + "'" +
                ", \n" + "ShortDescription='" + briefDescription + "'" +
                ", \n" + "LongDescription='" + completeDescription + "'" +
                ", \n" + "Keywords='" + lKeywords + "'\n" +
                '}';
    }

    public Catalog catalog() {
        return catalog;
    }

    public FluxoAtividadesServico activityFlow() {
        return this.activityFlow;
    }

    public boolean containsSearchTerm(String term) {
        String lower = term.toLowerCase();
        boolean contains = false;
        for (Keyword k : lKeywords) {
            if (k.toString().toLowerCase().contains(lower))
                contains = true;
        }
        return contains || title.toString().toLowerCase().contains(lower) ||
                briefDescription.toString().toLowerCase().contains(lower) ||
                completeDescription.toString().toLowerCase().contains(lower);
    }

    @Override
    public ServiceDTO toDTO() {
        return new ServiceDTO ( this.title.toString (),this.serviceCode.toString (),this.briefDescription.toString () );
    }
}
