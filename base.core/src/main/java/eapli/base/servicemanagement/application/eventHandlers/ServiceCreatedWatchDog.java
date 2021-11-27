package eapli.base.servicemanagement.application.eventHandlers;

import eapli.base.servicemanagement.application.CreateServiceController;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.domain.events.CreateServiceEvent;
import eapli.base.utils.ANSI;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.eventpubsub.EventHandler;
import eapli.framework.validations.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceCreatedWatchDog implements EventHandler {
    private static Logger LOGGER= LogManager.getLogger (ServiceCreatedWatchDog.class);
    @Override
    public void onEvent(final DomainEvent domainEvent) {
        Preconditions.ensure(domainEvent instanceof CreateServiceEvent );
        final CreateServiceEvent event=(CreateServiceEvent) domainEvent;
        final CreateServiceController controller=new CreateServiceController ();
        try{
            controller.addService ( event );
        }catch (final IntegrityViolationException e){
            LOGGER.error ( ANSI.format ("Unable to register new service on event",ANSI.RED ));
            e.printStackTrace ();
        }
    }
}
