package eapli.base.servicemanagement.domain;

import eapli.base.activity.domain.*;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.common.Icon;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.servicemanagement.domain.form.DataType;
import eapli.base.servicemanagement.domain.form.Form;
import eapli.base.servicemanagement.domain.form.FormDraft;
import eapli.base.servicemanagement.dto.ServiceDraftDTO;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Entity
@Where(clause="DELETED=0")
public class ServiceDraft implements AggregateRoot<ServiceCode>, DomainFactory<Service>,
                                        DTOable<ServiceDraftDTO>, ServiceBuildable<Service> {
    @Version
    private Long version;

    @Column(name="DELETED")
    private Integer deleted=0;

    @EmbeddedId
    private ServiceCode serviceCode;
    @Embedded
    private Title title;
    @ManyToOne
    private Catalog catalog;
    @Embedded
    private Icon icon;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Keyword> lKeywords;
    @Embedded
    private BriefDescription briefDescription;
    @Embedded
    private CompleteDescription completeDescription;
    @OneToOne(cascade=CascadeType.ALL)
    private FormDraft fillOutFormDraft;
    @OneToOne(cascade=CascadeType.ALL)
    private FormDraft feedbackFormDraft;
    private Integer formCounter;
    @OneToOne(cascade=CascadeType.ALL)
    private FluxoAtividadesServico activityFlow;
    @ManyToOne
    private Criticidade serviceCriticality;


    public ServiceDraft(final ServiceCode serviceCode, final Catalog catalog, final Icon icon,
                        final List<Keyword> lKeywords, final BriefDescription briefDescription,
                        final CompleteDescription completeDescription, final Title title,
                        final FormDraft fillOutFormDraft, final FormDraft feedbackFormDraft,
                        final int formCounter,final FluxoAtividadesServico activityFlow,final Criticidade criticality) {
        if(serviceCode==null ||title==null){
            throw new IllegalArgumentException ("The draft needs at least a service code and a title:" +
                    "serviceId="+serviceCode+"; title="+title+";\n");
        }
        this.serviceCode = serviceCode;
        this.catalog = catalog;
        this.icon=icon;
        this.lKeywords = new LinkedList<>(lKeywords);
        this.briefDescription = briefDescription;
        this.completeDescription = completeDescription;
        this.title = title;
        this.fillOutFormDraft = fillOutFormDraft;
        this.feedbackFormDraft = feedbackFormDraft;
        this.formCounter=formCounter;
        this.activityFlow=activityFlow;
        this.serviceCriticality = criticality;
    }

    public ServiceDraft() {
        //ORM
        this.activityFlow = new FluxoAtividadesServico();
        this.lKeywords=new LinkedList<> ();
    }

    @Override
    public Service build() {
        Preconditions.noneNull ( title,serviceCode,catalog,briefDescription,completeDescription,icon);
        Preconditions.nonEmpty ( lKeywords );
        Form fillOutForm= fillOutFormDraft.build ();
        Form feedbackForm=null;
        if(feedbackFormDraft!=null){
            feedbackForm=feedbackFormDraft.build ();
        }
        return new Service ( serviceCode, catalog,icon, lKeywords, briefDescription, completeDescription,
                State.READY_FOR_PUBLISHING, title, fillOutForm, feedbackForm,activityFlow.build(), serviceCriticality );
    }

    @Override
    public ServiceDraft withTitle(final String t) throws IllegalArgumentException{
        if(t!=null) {
            this.title = new Title ( t );
        }
        return this;
    }

    @Override
    public ServiceDraft withServiceId(final String serviceId) throws IllegalArgumentException{
        if(serviceId!=null){
            this.serviceCode =new ServiceCode ( serviceId );
        }
        return this;
    }
    @Override
    public ServiceDraft withCatalog(final Catalog catalog) throws IllegalArgumentException{
        this.catalog =catalog;
        return this;
    }
    @Override
    public ServiceDraft withIcon(final byte[] icon) throws IllegalArgumentException{
        if(icon!=null){
            this.icon=new Icon ( icon );
        }
        return this;
    }
    @Override
    public ServiceDraft withKeyword(final String s) throws IllegalArgumentException{
        Keyword k=new Keyword (s);
        if(lKeywords.contains ( k )){
            throw new IllegalArgumentException ("Service already contains the keyword "+s+"");
        }
        lKeywords.add ( new Keyword ( s ) );
        return this;
    }
    @Override
    public ServiceDraft withoutKeyword(final String s) throws IllegalArgumentException{
        Keyword k=new Keyword (s);
        if(!lKeywords.contains ( k )){
            throw new IllegalArgumentException ("Service doesn't contain the keyword "+s+"");
        }
        lKeywords.remove ( k );
        return this;
    }
    @Override
    public ServiceDraft withKeywords(final Collection<String> c) throws IllegalArgumentException{
        List<Keyword> l=new ArrayList<> (c.size ());
        for (String s : c) {
            l.add ( new Keyword (s) );
        }
        return this;
    }
    @Override
    public ServiceDraft withCompleteDescription(final String d) throws IllegalArgumentException {
        if(d!=null) {
            this.completeDescription = new CompleteDescription ( d );
        }
        return this;
    }
    @Override
    public ServiceDraft withBriefDescription(final String d) throws IllegalArgumentException {
        if(d!=null){
            this.briefDescription =new BriefDescription (d);
        }
        return this;
    }

    @Override
    public ServiceDraft withFilloutFormName(final String s) throws IllegalArgumentException {
       this.fillOutFormDraft.withName ( s );
       return this;
    }
    @Override
    public ServiceDraft withFilloutFormAttribute(final String name, final String description, final String label,
                                                 final String dataType, final String regex) throws IllegalArgumentException {
        this.fillOutFormDraft.withAttribute ( name,description,label,dataType,regex );
        return this;
    }

    @Override
    public ServiceDraft withFeedbackForm() throws IllegalArgumentException {
        this.feedbackFormDraft=new FormDraft ()
                .withName ( "Feedback Form" )
                .withServiceLevelId ( generateFormId () )
                .withAttribute ( "degreeOfSatisfaction","Indicate your degree of satisfaction, between 1 to 5",
                        "Rating", "NUMBER","[1-5]")
                .withAttribute("comments","If you have any additional comments feel free to share them",
                        "Additional Comments","TEXT","[.\n]*");
        return this;
    }
    @Override
    public ServiceDraft withoutFeedbackForm() throws IllegalArgumentException{
        if(this.feedbackFormDraft!=null){
            this.feedbackFormDraft=null;
            formCounter--;
        }
        return this;
    }

    @Override
    public ServiceDraft withCriticality(final Criticidade criticality) throws IllegalArgumentException{
        if(criticality !=null) {
           this.serviceCriticality = criticality;
        }
        return this;
    }


    @Override
    public ServiceDraft withApprovalActivity(String responsibleCategory) throws IllegalArgumentException {
        StringPredicates.isNullOrWhiteSpace(responsibleCategory);

        FormDraft approvalForm = new FormDraft().withName("Approval");
        approvalForm.withAttribute("Decision", "Attribute containing approval decision",
                "decision", DataType.BOOLEAN.toString(),"(true)|(false)");
        approvalForm.withAttribute("Justification", "Attribute containing the justification",
                "justification", DataType.TEXT.toString(),"[A-Za-z \n]+");
        approvalForm.withAttribute("Additional Comments", "Attribute containing additional comments",
                "comment", DataType.TEXT.toString(),"[A-Za-z \n]*").withServiceLevelId(generateFormId());
        AtividadeAprovacao atividadeAprovacao = new AtividadeAprovacao(responsibleCategory,approvalForm.build());
        this.activityFlow.withApprovalActivity(atividadeAprovacao);
        return this;
    }

    @Override
    public ServiceDraft withManualRealizationActivity(String formName, List<AttributeDTO> attributes, List<Equipa> teams,byte[] validateResponseScript) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName).withScript(validateResponseScript);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(teams,form);
        this.activityFlow.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);
        return this;
    }

    @Override
    public ServiceDraft withManualRealizationActivity(String formName, List<AttributeDTO> attributes, Colaborador selectedColaborador,byte[] validateResponseScript) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName).withScript(validateResponseScript);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(selectedColaborador,form);
        this.activityFlow.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);
        return this;
    }

    @Override
    public ServiceDraft withManualRealizationActivity(String formName, List<AttributeDTO> attributes, List<Equipa> teams) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(teams,form);
        this.activityFlow.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);
        return this;
    }

    @Override
    public ServiceDraft withManualRealizationActivity(String formName, List<AttributeDTO> attributes, Colaborador selectedColaborador) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(selectedColaborador,form);
        this.activityFlow.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);
        return this;
    }


    @Override
    public ServiceDraft withAutomaticRealizationActivity( byte[] script) {
        RealizacaoAutomatica a = new RealizacaoAutomatica(Script.valueOf(script));
        this.activityFlow.withRealizationActivity(TipoAtividadeRealizacao.AUTOMATICA,a);
        return this;
    }


    @Override
    public boolean sameAs(Object other) {
        return this.equals ( other );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        ServiceDraft that = (ServiceDraft) o;
        return Objects.equals ( serviceCode, that.serviceCode );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( version, serviceCode, lKeywords, briefDescription, completeDescription, title/*, form*/ );
    }

    @Override
    public ServiceCode identity() {
        return this.serviceCode;
    }

    @Override
    public String toString() {
        return "ServiceDraft{"
                + "ServiceCode='" + serviceCode + "'" +
                ", " + "Title='" + title + "'" +
                ", " + "Catalog='" + catalog + "'" +
                ", " + "Keywords='" + lKeywords + "'" +
                ", " + "BriefDescription='" + briefDescription + "'" +
                ", " + "CompleteDescription='" + completeDescription + "'" +
                '}';
    }
    @Override
    public ServiceDraftDTO toDTO(){
        List<String> keywords=new ArrayList<> (lKeywords.size ());
        for (Keyword k : lKeywords) {
            keywords.add ( k.toString () );
        }
        String title=null, serviceCode=null, briefDesc=null, completeDesc=null,flux=null;
        byte[]icon=null;
        CatalogDTO catalog=null;
        if(this.title!=null) title=this.title.toString ();
        if(this.serviceCode!=null) serviceCode=this.serviceCode.toString ();
        if(this.icon!=null)icon=this.icon.iconData ();
        if(this.briefDescription !=null) briefDesc=this.briefDescription.toString ();
        if(this.completeDescription !=null) completeDesc=this.completeDescription.toString ();
        if(this.catalog!=null) catalog=this.catalog.toDTO ();
        if(this.activityFlow!=null) flux = this.activityFlow.toString();

        return new ServiceDraftDTO (title,serviceCode,icon, briefDesc,completeDesc,keywords, catalog,flux);
    }

    public String subject() {
        return String.format("(%s) %s", serviceCode, title);
    }

    public Catalog catalog() {
        return catalog;
    }

    public boolean containsSearchTerm(String term) {
        return title.toString().toLowerCase().contains(term.toLowerCase());
    }

    private String generateFormId(){
        return ""+(formCounter++);
    }

    public void setDeleted() {
        this.deleted = 1;
    }
}
