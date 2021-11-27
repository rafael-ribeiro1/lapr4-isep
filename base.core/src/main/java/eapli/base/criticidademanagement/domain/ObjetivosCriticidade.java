package eapli.base.criticidademanagement.domain;

import eapli.framework.domain.model.ValueObject;


import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class ObjetivosCriticidade implements ValueObject {


    private static final long serialVersionUID = 1L;

    private final int tempoMaxAprovacao;

    private final int tempoMaxResolucao;

    private final int tempoMedioAprovacao;

    private final int tempoMedioResolucao;

    public ObjetivosCriticidade(final int tempoMaxAprovacao,final int tempoMaxResolucao,final int tempoMedioAprovacao,final int tempoMedioResolucao){
        if(tempoMaxAprovacao<0|| tempoMaxResolucao<0|| tempoMedioAprovacao<0||tempoMedioResolucao<0){
            throw new IllegalArgumentException("Can't define negative minutes to objectives");
        }
        this.tempoMaxAprovacao=tempoMaxAprovacao;
        this.tempoMaxResolucao=tempoMaxResolucao;
        this.tempoMedioAprovacao=tempoMedioAprovacao;
        this.tempoMedioResolucao=tempoMedioResolucao;
    }

    protected ObjetivosCriticidade (){
        this.tempoMaxAprovacao=-1;
        this.tempoMaxResolucao=-1;
        this.tempoMedioAprovacao=-1;
        this.tempoMedioResolucao=-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetivosCriticidade that = ( ObjetivosCriticidade) o;
        return this.tempoMedioResolucao==that.tempoMedioResolucao && this.tempoMaxResolucao==that.tempoMaxResolucao && this.tempoMedioAprovacao == that.tempoMedioAprovacao && this.tempoMaxAprovacao==that.tempoMaxAprovacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tempoMaxAprovacao,tempoMedioAprovacao,tempoMaxResolucao,tempoMedioResolucao);
    }

    @Override
    public String toString() {
        return String.format("Objetivos:\n Tempo Máximo Aprovacao : %d \n Tempo Médio Aprovacao: %d \n Tempo Máximo Resolucao : %d \n Tempo Médio Aprovacao:%d ",tempoMaxAprovacao,tempoMedioAprovacao,tempoMaxResolucao,tempoMedioResolucao);
    }
}
