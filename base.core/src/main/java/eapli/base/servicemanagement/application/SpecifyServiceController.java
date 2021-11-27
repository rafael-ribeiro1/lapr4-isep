package eapli.base.servicemanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.colaboratormanagement.application.viaDTO.ListCollaboratorDTOService;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.dto.ColaboradorDTOParser;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.servicemanagement.domain.*;
import eapli.base.servicemanagement.domain.events.CreateServiceEvent;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.servicemanagement.dto.ServiceDraftDTO;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.eventpubsub.EventPublisher;
import eapli.framework.infrastructure.eventpubsub.impl.inprocess.InProcessPubSub;
import eapli.framework.validations.Preconditions;
import org.hibernate.LazyInitializationException;

import java.util.*;

@UseCaseController
public class SpecifyServiceController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();
    private final ServiceDraftRepository serviceDraftRepository = factory.serviceDrafts ();
    private final Role role=BaseRoles.GESTOR_SH;
    private final EventPublisher dispatcher= InProcessPubSub.publisher ();
    @SuppressWarnings ( "rawtypes" )
    private ServiceBuildable builder;

    public SpecifyServiceController(final boolean isCreateServiceMenu) {
        if(isCreateServiceMenu)
            builder=new ServiceDraftBuilder ();
        else
            builder=new ServiceDraft();
    }

    public void createServiceDraft() throws IllegalArgumentException,IntegrityViolationException, ConcurrencyException{
        authz.ensureAuthenticatedUserHasAnyOf( role );
        final ServiceDraft s=((ServiceDraftBuilder)builder).build ();
        this.serviceDraftRepository.save ( s );
    }

    public void updateDraft(final ServiceDraftDTO dto){
        final Optional<ServiceDraft> optDraft=serviceDraftRepository.ofIdentity ( new ServiceCode (  dto.serviceCode ) );
        optDraft.ifPresent ( serviceDraft -> builder = serviceDraft
                .withTitle ( dto.title )
                .withServiceId ( dto.serviceCode )
                .withBriefDescription ( dto.briefDesc )
                .withCompleteDescription ( dto.completeDesc )
                .withCatalog ( getCatalog ( dto.catalog ) )
                .withIcon ( dto.icon )
                .withKeywords ( dto.keywords ) );
    }

    public void saveUpdatedServiceDraft() throws IllegalArgumentException,IntegrityViolationException, ConcurrencyException {
        authz.ensureAuthenticatedUserHasAnyOf( role );
        this.serviceDraftRepository.save ( (ServiceDraft)builder );
    }

    public void createServiceFromDraft(){
        authz.ensureAuthenticatedUserHasAnyOf( role );
        Preconditions.ensure (builder instanceof ServiceDraft);
        final DomainEvent event=new CreateServiceEvent ( (ServiceDraft)builder );
        dispatcher.publish ( event );
        //serviceDraftRepository.remove ( (ServiceDraft) builder );
        //allow to print the menu after the event has been published
        try {
            Thread.sleep ( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }
    //Soft delete
    public void removeServiceDraft(final ServiceDraft s){
        s.setDeleted ();
        serviceDraftRepository.save ( s );
    }

    public ServiceDraft getServiceDraftById(String id){
        return serviceDraftRepository.getServiceById ( id );
    }

    public void withTitle(final String t) throws IllegalArgumentException{
        builder.withTitle ( t );
    }

    public void withServiceCode(final String c) throws IllegalArgumentException{
        builder.withServiceId ( c );
    }

    public void withCatalog(final CatalogDTO catalog){
        Catalog c=getCatalog ( catalog );
        builder.withCatalog ( c );
    }

    public void withIcon(final byte[]icon) throws IllegalArgumentException{
        builder.withIcon ( icon );
    }

    public void withBriefDescription(final String d) throws IllegalArgumentException{
        builder.withBriefDescription ( d );
    }

    public void withCompleteDescription(final String d) throws IllegalArgumentException{
        builder.withCompleteDescription ( d );
    }

    public void withKeyword(final String k) throws IllegalArgumentException{
        builder.withKeyword ( k );
    }

    public void withoutKeyword(final String k) throws IllegalArgumentException{
        builder.withoutKeyword ( k );
    }

    public void withFeedbackForm() {
        builder.withFeedbackForm ();
    }

    public void withoutFeedbackForm() {
        builder.withoutFeedbackForm ();
    }

    public void withFillOutFormName(final String name){
        builder.withFilloutFormName ( name );
    }

    public void withFillOutFormAttribute(final String name, final String desc, final String label,
                                         final String dataType, final String regex){
        builder.withFilloutFormAttribute ( name,desc,label,dataType,regex );
    }

    public Iterable<CatalogDTO> allCatalogs(){
        authz.ensureAuthenticatedUserHasAnyOf( role );
        Iterable<Catalog> l= factory.catalogs ().findAll ();
        List<CatalogDTO> dtos=new ArrayList<> ();
        for (Catalog catalog : l) {
            dtos.add ( catalog.toDTO () );
        }
        return dtos;
    }

    public Iterable<Criticidade> allCriticalities() {
        authz.ensureAuthenticatedUserHasAnyOf(role);
        return factory.criticidades().findAll();
    }

    public Iterable<ServiceDraftDTO> allServices(){
        authz.ensureAuthenticatedUserHasAnyOf( role );
        Iterable<ServiceDraft> l= factory.serviceDrafts ().findAll ();
        List<ServiceDraftDTO> d=new ArrayList<> ();
        for (ServiceDraft s : l) {
            d.add ( s.toDTO () );
        }
        return d;
    }

    private Catalog getCatalog(final CatalogDTO catalog){
        Catalog c;
        try{
            Optional<Catalog> optCatalog=factory.catalogs ().ofIdentity ( catalog.id);
            c=optCatalog.orElse ( null );
        }catch (NullPointerException e){
            c=null;
        }
        return c;
    }

    public Iterable<Equipa> availableTeams() {
        return factory.equipas().findAll();
    }

    public void addApprovalActivity(String responsibleCategory) {
        builder.withApprovalActivity(responsibleCategory);
    }

    public void addManualRealizationActivity(String formName, List<AttributeDTO> attributes, List<Equipa> teams,byte[] validateResponseScript) {
        builder.withManualRealizationActivity(formName,attributes,teams,validateResponseScript);
    }

    public void addManualRealizationActivity(String formName, List<AttributeDTO> attributes, ColaboradorDTO colaborador,byte[] validateResponseScript) {
        Colaborador fromDto = new ColaboradorDTOParser(factory.colaboradores()).valueOf(colaborador);
        builder.withManualRealizationActivity(formName,attributes,fromDto,validateResponseScript);
    }

    public void addAutomaticRealizationActivity(byte[] script) {
        builder.withAutomaticRealizationActivity(script);
    }

    public void withCriticality(Criticidade criticality) {
        builder.withCriticality (criticality);
    }

    public Iterable<ColaboradorDTO> getAvailableColaboradores(CatalogDTO dto) {
        if(dto == null) return ListCollaboratorDTOService.allColaborators();
        ColaboradorRepository colabRepo = factory.colaboradores();
        Catalog catalog = factory.catalogs().ofIdentity(dto.id).get();
        List<Equipa> equipas = catalog.teamsWithAccess();
        Set<ColaboradorDTO> set = new HashSet<>();
        for(Equipa e:equipas){
            Iterable<Colaborador> ite = colabRepo.findAllColaboradoresPorEquipa(e);
            set.addAll(ListCollaboratorDTOService.convertListToColaboratorDTO(ite));
        }
        return set;
    }


}
