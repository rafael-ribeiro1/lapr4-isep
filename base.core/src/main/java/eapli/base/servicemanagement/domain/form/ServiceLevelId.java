package eapli.base.servicemanagement.domain.form;

import eapli.framework.domain.model.ValueObject;
import javax.persistence.Embeddable;

@Embeddable
class ServiceLevelId implements ValueObject {

    private final String serviceLevelId;

    public ServiceLevelId(final String serviceLevelId) {
        if(serviceLevelId==null||serviceLevelId.isBlank ())
            throw new IllegalArgumentException ("Service level id cannot be blank or null");
        this.serviceLevelId = serviceLevelId;
    }

    protected ServiceLevelId() {
        //ORM
        serviceLevelId=null;
    }

    @Override
    public String toString() {
        return "Form Id{" +
                "id='" + serviceLevelId + '\'' +
                '}';
    }
}
