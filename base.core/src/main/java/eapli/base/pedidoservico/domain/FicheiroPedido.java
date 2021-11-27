package eapli.base.pedidoservico.domain;

import eapli.base.common.Icon;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.util.Arrays;

@Embeddable
public class FicheiroPedido implements ValueObject {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] file;

    public FicheiroPedido(final byte[] file) {
        Preconditions.nonNull(file);

        this.file = Arrays.copyOf(file, file.length);
    }

    protected FicheiroPedido() {
        this.file = null;
    }

    public byte[] fileData(){
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FicheiroPedido file1 = (FicheiroPedido) o;
        return Arrays.equals(file, file1.file);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(file);
    }

}
