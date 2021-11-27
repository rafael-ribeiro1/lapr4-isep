package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryServiceDraftRepository extends InMemoryDomainRepository<ServiceDraft, ServiceCode> implements ServiceDraftRepository {

    static {
        InMemoryInitializer.init();
    }
    @Override
    public ServiceDraft getServiceById(String id){
        return matchOne ( e->e.identity ().equals ( new ServiceCode ( id ) ) )
                .orElse ( null );
    }

    @Override
    public List<ServiceDraft> searchServices(String term, Colaborador colab) {
        List<ServiceDraft> services = new ArrayList<>();
        for (ServiceDraft s : findAll()) {
            if (s.containsSearchTerm(term))
                services.add(s);
        }
        return services;
    }
}
