package eapli.base.colaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.util.CalendarPredicates;
import eapli.framework.time.util.Calendars;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Embeddable
public class DataNascimento implements ValueObject,Comparable<DataNascimento> {


    private static final long serialVersionUID = 1L;
    @Temporal(TemporalType.DATE)
    private Calendar date;

    public DataNascimento (final Calendar dataNascimento){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(dataNascimento == null) throw new IllegalArgumentException("A data nao pode ser nula");
        if(CalendarPredicates.isAfter(dataNascimento,new GregorianCalendar())){
            throw new IllegalArgumentException("A Data tem de ser anterior ao dia de hoje!");
        }
        if(currentYear-dataNascimento.get(Calendar.YEAR)<18){
            throw new IllegalArgumentException("O Colaborador tem de ter pelo menos 18 anos !");
        }
        if(currentYear-dataNascimento.get(Calendar.YEAR)>90){
            throw new IllegalArgumentException("O Colaborador é demasiado idoso para trabalhar !");
        }
        date = dataNascimento;
    }

    protected DataNascimento(){

    }

    public Calendar Data() {
        return date;
    }

    public static DataNascimento valueOf(final Calendar dataNascimento){
        return new DataNascimento(dataNascimento);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataNascimento)) {
            return false;
        }

        final DataNascimento that = (DataNascimento) o;
        return this.date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

    @Override
    public String toString() {
        return this.date.toString();
    }


    @Override
    public int compareTo(DataNascimento o) {
        return this.date.compareTo(o.date) ;
    }
}
