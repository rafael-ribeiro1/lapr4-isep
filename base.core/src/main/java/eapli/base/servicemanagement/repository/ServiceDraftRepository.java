package eapli.base.servicemanagement.repository;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ServiceDraftRepository extends DomainRepository<ServiceCode, ServiceDraft> {

    ServiceDraft getServiceById(String id);

    List<ServiceDraft> searchServices(String term, Colaborador colab);

}
