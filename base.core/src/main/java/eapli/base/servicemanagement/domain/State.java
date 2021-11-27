package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public enum State implements ValueObject {
    READY_FOR_PUBLISHING,
    PUBLISHED,
    REMOVED;
}
