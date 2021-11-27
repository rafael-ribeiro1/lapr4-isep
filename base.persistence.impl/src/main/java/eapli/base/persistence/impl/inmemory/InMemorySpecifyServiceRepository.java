package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.State;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemorySpecifyServiceRepository
        extends InMemoryDomainRepository<Service, ServiceCode>
        implements ServiceRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Service> searchServices(String term, Colaborador colab) {
        List<Service> services = new ArrayList<>();
        for (Service s : findAll()) {
            if (s.containsSearchTerm(term))
                services.add(s);
        }
        return services;
    }

    @Override
    public List<Service> findWithAccess(Colaborador colab) {
        List<Service> services = new ArrayList<>();
        for (Service s : findAll()) {
            for (Equipa e : s.catalog().teamsWithAccess()) {
                if (e.containsCollaborator(colab))
                    services.add(s);
            }
        }
        return services;
    }

    @Override
    public List<Service> getAllServicesExceptState(State state){
        List<Service> l=new ArrayList<> ();
        Iterable<Service> allServices=findAll ();
        for (Service s : allServices) {
            if(!s.state ().equals ( state ))
                l.add ( s );
        }
        return l;
    }

    @Override
    public List<Service> getAllUnpublishedServices() {
        return getAllServicesExceptState ( State.PUBLISHED );
    }

    @Override
    public void changeServiceState(String id, State state) {
        Optional<Service> s=findById ( new ServiceCode ( id ) );
        s.ifPresent ( service -> service.changeState ( state ) );
    }

    @Override
    public void publishService(final String id) {
        changeServiceState ( id,State.PUBLISHED );
    }
}
