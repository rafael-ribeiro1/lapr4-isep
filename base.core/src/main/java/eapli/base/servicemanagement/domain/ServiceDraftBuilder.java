package eapli.base.servicemanagement.domain;

import eapli.base.activity.domain.*;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.common.Icon;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.servicemanagement.domain.form.*;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.strings.util.StringPredicates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
Builder pattern
 */

public class ServiceDraftBuilder implements ServiceBuildable<ServiceDraft> {

    private ServiceCode serviceId;
    private Catalog catalog;
    private Icon icon;
    private List<Keyword> lKeywords;
    private BriefDescription shortDescription;
    private CompleteDescription completeDescription;
    private Title title;
    private FormDraft fillOutForm;
    private FormDraft feedbackFormDraft;
    private FluxoAtividadesServico serviceActivityFlux;
    private int formCounter;
    private Criticidade criticality;
    private FluxoAtividadesPedido ticketActivityFlux;

    public ServiceDraftBuilder() {
        this.lKeywords=new LinkedList<> ();
        this.fillOutForm =new FormDraft ();
        this.formCounter=1;
        this.fillOutForm.withServiceLevelId ( generateFormId () );
        this.serviceActivityFlux = new FluxoAtividadesServico();
    }

    @Override
    public ServiceDraft build(){
        if(serviceId==null ||title==null){
            throw new IllegalArgumentException ("The draft needs at least a service code and a title:" +
                    "serviceId="+serviceId+"; title="+title+";\n");
        }
        return new ServiceDraft (serviceId, catalog,icon, lKeywords, shortDescription, completeDescription,
                title, fillOutForm, feedbackFormDraft, formCounter, serviceActivityFlux, criticality );
    }
    @Override
    public ServiceDraftBuilder withServiceId(final String serviceId) throws IllegalArgumentException{
        this.serviceId =new ServiceCode ( serviceId );
        return this;
    }
    @Override
    public ServiceDraftBuilder withTitle(final String t) throws IllegalArgumentException{
        this.title = new Title ( t );
        return this;
    }
    @Override
    public ServiceDraftBuilder withCatalog(final Catalog catalog){
        this.catalog =catalog;
        return this;
    }
    @Override
    public ServiceDraftBuilder withIcon(final byte[] icon)  throws IllegalArgumentException{
        if(icon!=null)
            this.icon=new Icon ( icon );
        return this;
    }
    @Override
    public ServiceDraftBuilder withKeyword(final String s) throws IllegalArgumentException{
       Keyword k=new Keyword (s);
        if(lKeywords.contains ( k )){
            throw new IllegalArgumentException ("Service already contains the keyword "+s+"");
        }
        lKeywords.add ( new Keyword ( s ) );
        return this;
    }
    @Override
    public ServiceDraftBuilder withoutKeyword(final String s) throws IllegalArgumentException{
        Keyword k=new Keyword (s);
        if(!lKeywords.contains ( k )){
            throw new IllegalArgumentException ("Service doesn't contain the keyword "+s+"");
        }
        lKeywords.remove ( k );
        return this;
    }

    @Override
    public ServiceDraftBuilder withKeywords(final Collection<String> c) throws IllegalArgumentException{
        List<Keyword> l=new ArrayList<> (c.size ());
        for (String s : c) {
            l.add ( new Keyword (s) );
        }
        return this;
    }
    @Override
    public ServiceDraftBuilder withCompleteDescription(final String d) throws IllegalArgumentException{
        if(d!=null)
            this.completeDescription = new CompleteDescription ( d );
        return this;
    }
    @Override
    public ServiceDraftBuilder withBriefDescription(final String d) throws IllegalArgumentException {
        if(d!=null)
            this.shortDescription =new BriefDescription (d);
        return this;
    }

    @Override
    public ServiceDraftBuilder withFilloutFormName(final String s) throws IllegalArgumentException {
        this.fillOutForm.withName ( s );
        return this;
    }

    @Override
    public ServiceDraftBuilder withFilloutFormAttribute(final String name, final  String description, final String label,
                                                        final String dataType, final String regex) throws IllegalArgumentException {
        this.fillOutForm.withAttribute ( name,description,label,dataType,regex );
        return this;
    }

    @Override
    public ServiceDraftBuilder withFeedbackForm() throws IllegalArgumentException {
        this.feedbackFormDraft =new FormDraft ()
                .withName ( "Feedback Form" )
                .withServiceLevelId ( generateFormId () )
                .withAttribute ( "degreeOfSatisfaction","Indicate your degree of satisfaction, between 1 and 5",
                "Rating", "NUMBER","[1-5]");
        return this;
    }

    @Override
    public ServiceDraftBuilder withoutFeedbackForm() throws IllegalArgumentException{
        if(this.feedbackFormDraft !=null){
            this.feedbackFormDraft =null;
            formCounter--;
        }
        return this;
    }


    @Override
    public ServiceDraftBuilder withApprovalActivity(String responsibleCategory) throws IllegalArgumentException {
        StringPredicates.isNullOrWhiteSpace(responsibleCategory);

        FormDraft approvalForm = new FormDraft().withName("ApprovalForm");
        approvalForm.withAttribute("Decision", "Attribute containing approval decision",
                "decision", DataType.BOOLEAN.toString(),"(true)|(false)");
        approvalForm.withAttribute("Justification", "Attribute containing the justification",
                "justification", DataType.TEXT.toString(),"[A-Za-z \n]+");
        approvalForm.withAttribute("Additional Comments", "Attribute containing additional comments",
                "comment", DataType.TEXT.toString(),"[A-Za-z \n]*").withServiceLevelId(generateFormId ());
        AtividadeAprovacao atividadeAprovacao = new AtividadeAprovacao(responsibleCategory,approvalForm.build());
        this.serviceActivityFlux.withApprovalActivity(atividadeAprovacao);
        return this;
    }


    @Override
    public ServiceDraftBuilder withManualRealizationActivity(String formName, List<AttributeDTO> attributes, List<Equipa> teams) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);

        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(teams,form);
        this.serviceActivityFlux.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);

        return this;
    }

    @Override
    public ServiceDraftBuilder withManualRealizationActivity(String formName, List<AttributeDTO> attributes, Colaborador selectedColaborador) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();
        RealizacaoManual rm  = new RealizacaoManual(selectedColaborador,form);
        this.serviceActivityFlux.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);
        return this;
    }

    @Override
    public ServiceDraftBuilder withManualRealizationActivity(String formName, List<AttributeDTO> attributes, List<Equipa> teams,byte[] validateResponseScript) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName).withScript(validateResponseScript);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);

        }
        Form form = formDraft.build();
        RealizacaoManual rm = new RealizacaoManual(teams,form);
        this.serviceActivityFlux.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);

        return this;
    }

    @Override
    public ServiceDraftBuilder withManualRealizationActivity(String formName, List<AttributeDTO> attributes, Colaborador selectedColaborador,byte[] validateResponseScript) throws IllegalArgumentException {
        FormDraft formDraft = new FormDraft();
        formDraft.withServiceLevelId(generateFormId ()).withName(formName).withScript(validateResponseScript);
        for(AttributeDTO a : attributes){
            formDraft.withAttribute(a.name,a.description,a.label,a.dataType,a.regex);
        }
        Form form = formDraft.build();

        RealizacaoManual rm  = new RealizacaoManual(selectedColaborador,form);
        this.serviceActivityFlux.withRealizationActivity(TipoAtividadeRealizacao.MANUAL,rm);

        return this;
    }


    @Override
    public ServiceDraftBuilder withAutomaticRealizationActivity(byte[] script) {
        RealizacaoAutomatica a = new RealizacaoAutomatica(Script.valueOf(script));
        this.serviceActivityFlux.withRealizationActivity(TipoAtividadeRealizacao.AUTOMATICA,a);
        return this;
    }

    @Override
    public ServiceDraftBuilder withCriticality(Criticidade criticality) throws IllegalArgumentException{
        if(criticality ==null)
            throw new IllegalArgumentException("Criticality level cannot be null");
        this.criticality = criticality;
        return this;
    }

    private String generateFormId(){
        return ""+(formCounter++);
    }
}
