package eapli.base.common;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.util.Arrays;

@Embeddable
public class Icon implements ValueObject {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] icon;

    public Icon(final byte[] icon) {
        if(icon==null){
            throw new IllegalArgumentException ("Icon cannot be null");
        }
        this.icon = Arrays.copyOf(icon, icon.length);
    }

    protected Icon() {
        this.icon = null;
    }

    public byte[] iconData(){
        return Arrays.copyOf ( icon,icon.length );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Icon icon1 = (Icon) o;
        return Arrays.equals(icon, icon1.icon);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(icon);
    }

}
