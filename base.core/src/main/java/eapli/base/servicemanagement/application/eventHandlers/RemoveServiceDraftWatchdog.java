package eapli.base.servicemanagement.application.eventHandlers;

import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.domain.events.CreateServiceEvent;
import eapli.base.servicemanagement.domain.events.RemoveServiceDraftEvent;
import eapli.base.utils.ANSI;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.eventpubsub.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveServiceDraftWatchdog implements EventHandler {
    private static Logger LOGGER= LogManager.getLogger (RemoveServiceDraftWatchdog.class);
    final SpecifyServiceController controller=new SpecifyServiceController ( false );
    @Override
    public void onEvent(DomainEvent domainEvent) {
        final RemoveServiceDraftEvent event=(RemoveServiceDraftEvent) domainEvent;
        try{
            controller.removeServiceDraft ( event.draft () );
        }catch (final IntegrityViolationException e){
            LOGGER.error ( ANSI.format ("Unable to remove service draft on event",ANSI.RED ));
            e.printStackTrace ();
        }catch (final ConcurrencyException e){
            //reload from the db
            ServiceDraft s=controller.getServiceDraftById ( event.draft ().identity ().toString () );
            s.setDeleted ();
        }
    }
}
