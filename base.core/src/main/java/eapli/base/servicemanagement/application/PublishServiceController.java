package eapli.base.servicemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.dto.ServiceDTO;
import eapli.base.servicemanagement.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class PublishServiceController {
    private final ServiceRepository serviceRepository = PersistenceContext.repositories().services ();

    public List<ServiceDTO> getAllUnpublishedServices(){
        List<Service> l=serviceRepository.getAllUnpublishedServices();
        List<ServiceDTO>lDTO=new ArrayList<> (l.size ());
        l.forEach ( s->lDTO.add ( s.toDTO () ) );
        return lDTO;
    }

    public void publishService(String id){
        serviceRepository.publishService(id);
    }
}
