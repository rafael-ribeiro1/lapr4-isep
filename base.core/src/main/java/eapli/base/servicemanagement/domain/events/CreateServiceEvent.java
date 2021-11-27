package eapli.base.servicemanagement.domain.events;

import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.framework.domain.events.DomainEventBase;

public class CreateServiceEvent extends DomainEventBase {
    private final ServiceDraft draft;

    public CreateServiceEvent(final ServiceDraft draft){
        this.draft=draft;
    }
    public Service buildService(){
        return draft.build ();
    }

    public ServiceDraft draft() {
        return draft;
    }

    @Override
    public String toString() {
        return "CreateService("+draft.toString ()+")";
    }
}
