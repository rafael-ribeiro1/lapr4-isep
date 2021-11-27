package eapli.base.colaboratormanagement.domain;

import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NumeroContacto {

    private static final long serialVersionUID = 1L;
   final private String contactNumber;

   final private static  int ALLOWED_SIZE = 9;

    public NumeroContacto(final String  numContacto){
            if(StringPredicates.isNullOrWhiteSpace(numContacto))
                throw new IllegalArgumentException("O numéro nao pode ser vazio");

        if(!StringPredicates.containsDigit(numContacto))
            throw new IllegalArgumentException("Contact number needs to be a number!");

            if(numContacto.length()!=ALLOWED_SIZE)
                throw new IllegalArgumentException("O numéro nao tem o numéro de digitos corretos ");

            contactNumber=numContacto;
    }
    protected NumeroContacto(){
        contactNumber="";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeroContacto that = (NumeroContacto) o;
        return this.contactNumber==that.contactNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contactNumber);
    }

    @Override
    public String toString() {
        return contactNumber;
    }

}
