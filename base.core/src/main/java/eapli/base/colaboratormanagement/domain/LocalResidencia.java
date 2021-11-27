package eapli.base.colaboratormanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.Version;

@Embeddable
public class LocalResidencia implements ValueObject , Comparable<LocalResidencia> {



    private static final long serialVersionUID = 1L;

   final private String distrito;

  final  private String concelho;

    public LocalResidencia(final String distrito , final String concelho ){
        if(StringPredicates.isNullOrWhiteSpace(distrito) || StringPredicates.isNullOrWhiteSpace(concelho)){
            throw new IllegalArgumentException("O distrito/concelho não pode estar vazio");
        }
        this.distrito=distrito;
        this.concelho=concelho;
    }

    protected LocalResidencia(){
        distrito="";
        concelho="";
    }

    public String distrito() {
        return distrito;
    }

    public String concelho() {
        return concelho;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalResidencia)) {
            return false;
        }

        final LocalResidencia that = (LocalResidencia) o;
        return this.distrito.equals(that.distrito) && this.concelho.equals(that.concelho);
    }

    @Override
    public int hashCode() {
        return this.distrito.hashCode()+this.concelho.hashCode();
    }

    @Override
    public String toString() {
        return String.format(" %s - %s ",distrito,concelho) ;
    }

    @Override
    public int compareTo(LocalResidencia localResidencia) {
        int cmp = this.distrito.compareTo(localResidencia.distrito);
        if(cmp == 0) cmp = this.concelho.compareTo(localResidencia.concelho);
        return cmp;
    }
}
