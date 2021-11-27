package eapli.base.colaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.Version;

@Embeddable
public class EmailInstitucional implements ValueObject, Comparable<EmailInstitucional> {


    private static final long serialVersionUID = 1L;

    final private String email;

    public EmailInstitucional(final String emailInstitucional ){
        if(StringPredicates.isNullOrWhiteSpace(emailInstitucional)){
            throw new IllegalArgumentException("Email can't be null or empty");
        }
        if(!StringPredicates.isEmail(emailInstitucional)){
            throw new IllegalArgumentException("Email does not follow the correct format");
        }
        this.email=emailInstitucional;
    }

    protected EmailInstitucional(){
        // for ORM
        email = "";
    }


    public static EmailInstitucional valueOf (final String emaillInstitucional){
        return new EmailInstitucional(emaillInstitucional);
     }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailInstitucional)) {
            return false;
        }

        final EmailInstitucional that = (EmailInstitucional) o;
        return this.email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public int compareTo(EmailInstitucional o) {
        return this.email.compareTo(o.email);
    }
}
