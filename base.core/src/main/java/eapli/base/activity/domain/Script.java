package eapli.base.activity.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@Embeddable
public class Script implements ValueObject {


    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] script;


    public Script(byte[] script) {
        Preconditions.nonNull(script);
        this.script = script;
    }

    protected Script() {
        this.script = null;
    }

    public byte[] toByteArray(){
        return Arrays.copyOf ( this.script,this.script.length );
    }

    public static Script valueOf(byte[] script)  {
        return new Script(script);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Script)) return false;
        Script script1 = (Script) o;
        return Arrays.equals(script, script1.script);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(script);
    }

    @Override
    public String toString() {
        return new String(script, StandardCharsets.UTF_8);
    }

}
