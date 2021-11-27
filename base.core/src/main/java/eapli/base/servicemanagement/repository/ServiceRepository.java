package eapli.base.servicemanagement.repository;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends DomainRepository<ServiceCode, Service> {

    List<Service> searchServices(String term, Colaborador colab);

    List<Service> findWithAccess(Colaborador colab);

    Optional<Service> findById(ServiceCode id);

    List<Service> getAllServicesExceptState(State state);

    List<Service> getAllUnpublishedServices();

    void changeServiceState(final String id, State state);

    void publishService(String id);
}
