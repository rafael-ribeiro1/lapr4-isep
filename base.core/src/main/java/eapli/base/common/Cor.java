package eapli.base.common;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Cor implements ValueObject {

    private final Integer red;
    private final Integer green;
    private final Integer blue;
    private static final int MAX_VALUE = 255;
    private static final int MIN_VALUE = 0;

    public Cor(final Integer red,final Integer green,final Integer blue) {
        Preconditions.noneNull(red, green, blue);
        if (red > MAX_VALUE || red < MIN_VALUE || green > MAX_VALUE || green < MIN_VALUE ||
                blue > MAX_VALUE || blue < MIN_VALUE )
            throw new IllegalArgumentException("At least one parameter is not between [0,255]");
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Cor() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public Integer red() {
        return red;
    }

    public Integer green() {
        return green;
    }

    public Integer blue() {
        return blue;
    }

    public static Cor valueOf(final Integer red,final Integer green,final Integer blue) {
        return new Cor(red,green,blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cor)) return false;
        Cor cor = (Cor) o;
        return red.equals(cor.red) && green.equals(cor.green) && blue.equals(cor.blue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    @Override
    public String toString() {
        return String.format("%-3d %-3d %-3d", red, green, blue);
    }
}
