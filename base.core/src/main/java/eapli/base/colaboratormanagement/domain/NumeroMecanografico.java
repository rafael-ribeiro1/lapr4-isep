/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.colaboratormanagement.domain;

import eapli.base.Application;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class NumeroMecanografico implements ValueObject, Comparable<NumeroMecanografico> {

    private static final long serialVersionUID = 1L;

    final private static int MAX_LENGTH = Application.settings().getMechNumMaxLength();

    final private String numeroMecanografico;

    public NumeroMecanografico(final String mecanographicNumber) {
        if (StringPredicates.isNullOrWhiteSpace(mecanographicNumber) ) {
            throw new IllegalArgumentException(
                    "Mecanographic Number should neither be null nor empty");
        }
        if(StringPredicates.containsAlpha(mecanographicNumber))
            throw new IllegalArgumentException("Mecanographic number needs to be a number!");

       if(mecanographicNumber.length()>MAX_LENGTH){
           throw new IllegalArgumentException("O numéro mecanografico excede o tamanho imposto !");
       }
        this.numeroMecanografico =mecanographicNumber;
    }

    protected NumeroMecanografico() {
        numeroMecanografico="";
    }

    public static NumeroMecanografico valueOf(final String mecanographicNumber) {
        return new NumeroMecanografico(mecanographicNumber);
    }

    public String numeroMecanografico() {
        return numeroMecanografico;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumeroMecanografico)) {
            return false;
        }

        final NumeroMecanografico that = (NumeroMecanografico) o;
        return this.numeroMecanografico.equals(that.numeroMecanografico);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return this.numeroMecanografico;
    }

    @Override
    public int compareTo(final NumeroMecanografico arg0) {
        return this.numeroMecanografico.compareTo(arg0.numeroMecanografico);
    }
}
