package eapli.base.servicemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.events.CreateServiceEvent;
import eapli.base.servicemanagement.domain.events.RemoveServiceDraftEvent;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.eventpubsub.EventPublisher;
import eapli.framework.infrastructure.eventpubsub.impl.inprocess.InProcessPubSub;

@UseCaseController
public class CreateServiceController {
    private final ServiceRepository serviceRepository = PersistenceContext.repositories().services ();
    private final EventPublisher dispatcher= InProcessPubSub.publisher ();


    public Service addService(final CreateServiceEvent createEvent){
        final Service s=createEvent.buildService ();
        serviceRepository.save ( s );
        //notify interested parties
        final DomainEvent removeDraftEvent=new RemoveServiceDraftEvent ( createEvent.draft () );
        dispatcher.publish ( removeDraftEvent );
        return s;
    }
}
