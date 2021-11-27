package eapli.base.activity.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.util.CalendarPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class DataLimite implements ValueObject, Comparable<DataLimite> {

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;

    public DataLimite(final Calendar data) {
        Preconditions.nonNull(data);
        if (CalendarPredicates.isBeforeToday(data))
            throw new IllegalArgumentException("Activity deadline must be set to after today.");
        this.data = data;
    }

    protected DataLimite() {
        data = null;
    }

    public static DataLimite valueOf(Calendar data) {
        return new DataLimite(data);
    }

    public Calendar data() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d %dh%d", data.get(Calendar.DAY_OF_MONTH), data.get(Calendar.MONTH)+1, data.get(Calendar.YEAR),
                data.getTime().getHours(), data.getTime().getMinutes());
    }

    @Override
    public int compareTo( DataLimite o) {
        return this.data.compareTo(o.data);
    }
}
