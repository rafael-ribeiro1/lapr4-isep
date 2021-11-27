package eapli.base.colaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NomeCurto implements ValueObject {

    private static final long serialVersionUID = 1L;
   final  private String firstName;

    final private String lastName;

    private final static int MAX_LENGTH = 30;


    public NomeCurto(final String primeiroNome,final String ultimoNome){
        if(StringPredicates.isNullOrWhiteSpace(primeiroNome))
            throw  new IllegalArgumentException("O primeiro nome nao pode ser vazio");

        if(StringPredicates.isNullOrWhiteSpace(ultimoNome))
            throw new IllegalArgumentException("O ultimo nome nao pode ser vazio");

        if(primeiroNome.length()+ultimoNome.length()>MAX_LENGTH)
            throw new IllegalArgumentException("O tamanho maximo do nome  é de 30 caracteres");

        this.firstName =primeiroNome;
        this.lastName=ultimoNome;
    }

    protected NomeCurto(){
        this.lastName="";
        this.firstName="";
    }

    public String primeiroNome() {
        return firstName;
    }

    public String ultimoNome() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomeCurto that = (NomeCurto) o;
        return this.firstName.equals(that.firstName) && this.lastName.equals(that.lastName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstName,this.lastName);
    }

    @Override
    public String toString() {
        return String.format("%s %s",this.firstName,this.lastName);
    }




}
