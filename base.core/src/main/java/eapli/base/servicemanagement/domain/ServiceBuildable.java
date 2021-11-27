package eapli.base.servicemanagement.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.servicemanagement.domain.form.DataType;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.domain.model.DomainFactory;

import java.util.Collection;
import java.util.List;

public interface ServiceBuildable<T extends DomainEntity<?>> extends DomainFactory<T> {
    T build();

    ServiceBuildable<T> withServiceId(final String serviceId) throws IllegalArgumentException;

    ServiceBuildable<T> withTitle(final String t) throws IllegalArgumentException;

    ServiceBuildable<T> withCatalog(final Catalog catalog);

    ServiceBuildable<T> withIcon(final byte[] icon)  throws IllegalArgumentException;

    ServiceBuildable<T> withKeyword(final String s)  throws IllegalArgumentException;

    ServiceBuildable<T> withoutKeyword(final String s)  throws IllegalArgumentException;

    ServiceBuildable<T> withKeywords(final Collection<String> c) throws IllegalArgumentException;

    ServiceBuildable<T> withCompleteDescription(final String d) throws IllegalArgumentException;

    ServiceBuildable<T> withBriefDescription(final String d) throws IllegalArgumentException;

    ServiceBuildable<T> withFilloutFormName(final String s) throws IllegalArgumentException;

    ServiceBuildable<T> withFilloutFormAttribute(final String name, final String description, final  String label,
                                                 final String dataType, final String regex) throws IllegalArgumentException;

    ServiceBuildable<T> withFeedbackForm() throws IllegalArgumentException;

    ServiceBuildable<T> withoutFeedbackForm() throws IllegalArgumentException;

    ServiceBuildable<T> withApprovalActivity(final String responsibleCategory)throws IllegalArgumentException;
    ServiceBuildable<T> withManualRealizationActivity(final String formName,final  List<AttributeDTO> attributes,
                                                      final List<Equipa> teams)throws IllegalArgumentException;
    ServiceBuildable<T> withManualRealizationActivity(final String formName,final  List<AttributeDTO> attributes,
                                                      final Colaborador selectedColaborador)throws  IllegalArgumentException;

    ServiceBuildable<T> withManualRealizationActivity(final String formName,final  List<AttributeDTO> attributes,
                                                      final List<Equipa> teams,byte[] validateResponseScript)throws IllegalArgumentException;
    ServiceBuildable<T> withManualRealizationActivity(final String formName,final  List<AttributeDTO> attributes,
                                                      final Colaborador selectedColaborador,byte[] validateResponseScript)throws IllegalArgumentException;

    ServiceBuildable<T>  withAutomaticRealizationActivity(final byte[] script);

    ServiceBuildable<T> withCriticality(final Criticidade criticality);
}
