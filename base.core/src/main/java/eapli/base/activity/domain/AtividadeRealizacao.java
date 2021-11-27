package eapli.base.activity.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class AtividadeRealizacao extends Atividade implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private TipoAtividadeRealizacao tipoAtividadeRealizacao;

    AtividadeRealizacao(Long id, DataLimite dataLimite, TipoAtividadeRealizacao tipoAtividadeRealizacao) {
        super(id, dataLimite);
        Preconditions.nonNull(tipoAtividadeRealizacao);
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
    }

    AtividadeRealizacao(EstadoAtividade estado, DataLimite dataLimite, TipoAtividadeRealizacao tipoAtividadeRealizacao) {
        super(estado, dataLimite);
        Preconditions.nonNull(tipoAtividadeRealizacao);
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
    }

    AtividadeRealizacao(EstadoAtividade estado, TipoAtividadeRealizacao tipoAtividadeRealizacao) {
        super(estado);
        Preconditions.nonNull(tipoAtividadeRealizacao);
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
    }

    public AtividadeRealizacao(TipoAtividadeRealizacao tipoAtividadeRealizacao) {
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
    }

    protected AtividadeRealizacao() {
        super();
    }

    public abstract AtividadeRealizacao cloneForRequest(Calendar dataLimite);

    @Override
    public String toString() {
        return String.format("%s | %s Realization Activity",super.toString(), tipoAtividadeRealizacao.tipoAtividade);
    }
}
