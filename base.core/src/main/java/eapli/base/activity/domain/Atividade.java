package eapli.base.activity.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Objects;

@Entity
//1 tabela por classe + Chaves estrangeiras
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Atividade implements ValueObject {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoAtividade estado;

    @Embedded
    private DataLimite dataLimite;

    Atividade(Long id, DataLimite dataLimite) {
        Preconditions.noneNull(id, dataLimite);
        this.id = id;
        this.dataLimite = dataLimite;
    }

    Atividade(EstadoAtividade estado) {
        Preconditions.noneNull(estado);
        this.estado = estado;
    }

    Atividade(EstadoAtividade estado, DataLimite dataLimite) {
        Preconditions.noneNull(estado, dataLimite);
        this.estado = estado;
        this.dataLimite = dataLimite;
    }

    protected Atividade() {

    }

    public DataLimite dataLimite() {
        return dataLimite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atividade)) return false;
        Atividade atividade = (Atividade) o;
        return id.equals(atividade.id);
    }

    public void updateEstadoAtividade(EstadoAtividade estado) {
        this.estado = estado;
    }

    public EstadoAtividade estado() {
        return this.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String detailedInformation(){
        return String.format("%n-------Information about activity--------%nCurrent State:%s%n",estado.toString(), dataLimite.toString());
    }

    @Override
    public String toString() {
        return String.format("Activity %d | Deadline: %s", id, dataLimite.toString());
    }



}
