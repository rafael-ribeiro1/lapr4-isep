package eapli.base.servicemanagement.domain.events;

import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.framework.domain.events.DomainEventBase;

public class RemoveServiceDraftEvent extends DomainEventBase {
    private final ServiceDraft draft;

    public RemoveServiceDraftEvent(ServiceDraft draft) {
        this.draft = draft;
    }

    public ServiceDraft draft() {
        return draft;
    }

    @Override
    public String toString() {
        return "RemoveServiceDraft("+draft.toString ()+")";
    }
}
