package eapli.base.colaboratormanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NomeCompleto  implements ValueObject {
    private static final long serialVersionUID = 1L;
    final private String nomeCompleto;

    private final static int MAX_LENGTH = 80;

    public NomeCompleto(final String nome) {
        if(StringPredicates.isNullOrWhiteSpace(nome))
            throw new IllegalArgumentException("O nome nao pode ser vazio");
        if(nome.length()>MAX_LENGTH)
            throw new IllegalArgumentException("O tamanho máximo de um nome é 80 caracteres");
        this.nomeCompleto= nome;
    }

    protected NomeCompleto(){
                nomeCompleto="";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomeCompleto that = (NomeCompleto) o;
        return this.nomeCompleto.equals(that.nomeCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nomeCompleto);
    }

    @Override
    public String toString() {
        return this.nomeCompleto;
    }

}
