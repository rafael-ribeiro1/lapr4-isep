package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.servicemanagement.dto.ServiceDraftDTO;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class SpecifyServiceUIControllerFacade {
    private final SpecifyServiceController controller;
    private ServiceDraftDTO draftDTO;

    public SpecifyServiceUIControllerFacade(final boolean isCreateServiceMenu) {
        this.controller=new SpecifyServiceController (isCreateServiceMenu);
        this.draftDTO=new ServiceDraftDTO ();
    }

    protected void setTitle(final String t){
        this.controller.withTitle ( t );
        this.draftDTO.title=t;
    }

    protected String getTitle(){
        return draftDTO.title;
    }

    protected void setServiceCode(final String serviceCode){
        this.controller.withServiceCode ( serviceCode );
        this.draftDTO.serviceCode=serviceCode;
    }

    protected String getServiceCode(){
        return this.draftDTO.serviceCode;
    }

    protected void setCompleteDescription(final String description) throws IllegalArgumentException {
        this.controller.withCompleteDescription ( description );
        this.draftDTO.completeDesc=description;
    }

    protected String getCompleteDescription(){
        return this.draftDTO.completeDesc;
    }

    protected void setBriefDescription(final String description) throws IllegalArgumentException {
        this.controller.withBriefDescription ( description );
        this.draftDTO.briefDesc=description;
    }

    protected String getBriefDescription(){
        return this.draftDTO.briefDesc;
    }

    protected void setCatalog(final CatalogDTO catalogDTO){
        this.controller.withCatalog ( catalogDTO );
        this.draftDTO.catalog=catalogDTO;
    }

    protected void setCriticality(final Criticidade criticality){
        this.controller.withCriticality (criticality);
    }

    protected CatalogDTO getCatalog(){
        return this.draftDTO.catalog;
    }

    protected void setIcon(final byte[]icon) throws IllegalArgumentException {
        controller.withIcon ( icon );
        this.draftDTO.icon= Arrays.copyOf (icon,icon.length);
    }

    protected void withKeyword(final String keyword){
        this.controller.withKeyword ( keyword );
        this.draftDTO.keywords.add ( keyword );
    }

    protected Iterable<String> keywords(){
        return draftDTO.keywords;
    }

    protected boolean containsKeyword(final String keyword){
        return draftDTO.keywords.contains ( keyword );
    }

    protected void withoutKeyword(final String keyword){
        this.controller.withoutKeyword ( keyword );
        this.draftDTO.keywords.remove ( keyword );
    }

    protected void setFeedbackForm(){
        controller.withFeedbackForm();
    }

    protected void removeFeedbackForm(){
        controller.withoutFeedbackForm();
    }

    protected Void addFillOutFormName(final String name){
        controller.withFillOutFormName ( name );
        return null;
    }

    protected Void addFillOutFormAttribute(final AttributeDTO dto){
        controller.withFillOutFormAttribute ( dto.name,dto.description,dto.label,dto.dataType,dto.regex );
        return null;
    }

    public void addApprovalActivity(String responsibleCategory) {
        controller.addApprovalActivity(responsibleCategory);
    }

    public void addManualRealizationActivity(String formName, List<AttributeDTO> attributes, Set<Equipa> teams,byte[] validateResponseScript) {
        List<Equipa> selectedTeams = new ArrayList<>(teams);
        controller.addManualRealizationActivity(formName,attributes,selectedTeams,validateResponseScript);
    }

    public void addManualRealizationActivity(String formName, List<AttributeDTO> attributes, ColaboradorDTO selectedColaborador,byte[] validateResponseScript) {
        controller.addManualRealizationActivity(formName,attributes,selectedColaborador,validateResponseScript);
    }

    public void addAutomaticRealizationActivity(byte[] content) {
        controller.addAutomaticRealizationActivity(content);
    }


    protected Iterable<CatalogDTO> allCatalogs() {
        return controller.allCatalogs ();
    }

    protected Iterable<ServiceDraftDTO> allServices(){
        return controller.allServices ();
    }

    protected void setDTO(ServiceDraftDTO dto){
        this.draftDTO=dto;
    }

    @Deprecated
    protected void selectServiceDraftToLoad(){
        Iterable<ServiceDraftDTO> drafts = allServices ();
        SingleSelectionWidget<ServiceDraftDTO> select=new SingleSelectionWidget<> ( drafts );
        select.doSelection ();
        this.draftDTO=select.selectedItem ();
        this.controller.updateDraft ( this.draftDTO );
    }

    protected void loadService(ServiceDraftDTO dto){
        this.controller.updateDraft ( dto );
    }

    protected void reloadServiceDraft(){
        this.controller.updateDraft ( this.draftDTO );
    }

    protected Iterable<Equipa> availableTeams(){
        return controller.availableTeams();
    }

    protected void printStatus(){
        System.out.println ("Service");
        final String title= draftDTO.title;
        final String serviceCode= draftDTO.serviceCode;
        final byte[]icon= draftDTO.icon;
        final String briefDesc= draftDTO.briefDesc;
        final String completeDesc = draftDTO.completeDesc;
        final String fluxoActivity = draftDTO.fluxInfo;
        System.out.println ("Title: "+title);
        System.out.println ("Service Code: "+serviceCode);
        System.out.println ("Catalog: "+ draftDTO.catalog);
        System.out.print ( "Keywords: " );
        printKeywords ();
        System.out.println ("Brief Description: "+briefDesc);
        System.out.println ("Complete Description: "+completeDesc);
        System.out.println ("Icon: "+ (icon==null ?"Not selected":"Selected") );
        System.out.println("Activity Flux Info:\n"+fluxoActivity);
    }

    protected void printKeywords(){
        for (final String k : draftDTO.keywords) {
            System.out.printf ( "%s; ",k );
        }
        System.out.println ();
    }

    protected void save(final boolean isCreateService) throws IllegalArgumentException, IntegrityViolationException, ConcurrencyException {
        if(isCreateService)
            controller.createServiceDraft ();
        else
            controller.saveUpdatedServiceDraft ();
    }

    protected void finalizeServiceCreation() throws IllegalArgumentException, IntegrityViolationException, ConcurrencyException{
        controller.createServiceFromDraft ();
    }

    public Iterable<Criticidade> allCriticalities() {
        return controller.allCriticalities ();
    }

    public Iterable<ColaboradorDTO> availableColaboradores() {
        return controller.getAvailableColaboradores(this.getCatalog());
    }

    @Deprecated
    protected boolean addKeyword(final String keyword) throws IllegalArgumentException {
        if (draftDTO.keywords.contains ( keyword ))
            throw new IllegalArgumentException ( "Service already contains the keyword " + keyword + "" );
        controller.withKeyword ( keyword );
        return draftDTO.keywords.add ( keyword );
    }

    @Deprecated
    protected boolean removeKeyword() throws IllegalArgumentException{
        SingleSelectionWidget<String> removeSelect=new SingleSelectionWidget<> ( draftDTO.keywords );
        removeSelect.doSelection ();
        String remove=removeSelect.selectedItem ();
        if(!draftDTO.keywords.contains ( remove ))
            throw new IllegalArgumentException ("Service doesn't contain the keyword "+remove);
        controller.withoutKeyword ( remove );
        return draftDTO.keywords.remove ( remove );
    }
}
