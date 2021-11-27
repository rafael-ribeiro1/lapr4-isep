package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ServiceCode implements ValueObject, Comparable<ServiceCode> {
    private final String serviceCode;

    public ServiceCode(String serviceCode) {
        if(serviceCode==null||serviceCode.isBlank ()){
            throw new IllegalArgumentException ("Service code cannot be null or empty");
        }
        this.serviceCode=serviceCode;

    }

    protected ServiceCode() {
        //ORM
        serviceCode=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCode that = (ServiceCode) o;
        return Objects.equals(serviceCode, that.serviceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceCode);
    }

    @Override
    public int compareTo(ServiceCode o) {
        Preconditions.nonNull (o);
        return this.serviceCode.compareTo ( o.serviceCode );
    }

    @Override
    public String toString() {
        return serviceCode;
    }
}
