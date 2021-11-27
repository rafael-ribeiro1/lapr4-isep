package eapli.base.servicemanagement.domain;

import eapli.base.activity.domain.AtividadeAprovacao;
import eapli.base.activity.domain.AtividadeRealizacao;
import eapli.base.activity.domain.TipoAtividadeRealizacao;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FluxoAtividadesServico implements ValueObject {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private AtividadeAprovacao atividadeAprovacao;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private AtividadeRealizacao atividadeRealizacao;

    @Enumerated(EnumType.STRING)
    private TipoAtividadeRealizacao tipoAtividadeRealizacao;

    public FluxoAtividadesServico() {
    }

    public FluxoAtividadesServico(AtividadeAprovacao atividadeAprovacao,TipoAtividadeRealizacao tipoAtividadeRealizacao,AtividadeRealizacao atividadeRealizacao){
        Preconditions.noneNull(atividadeRealizacao, tipoAtividadeRealizacao);
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
        this.atividadeRealizacao=atividadeRealizacao;
        this.atividadeAprovacao=atividadeAprovacao;
    }

    public FluxoAtividadesServico build(){
        Preconditions.noneNull(atividadeRealizacao);
        return new FluxoAtividadesServico(atividadeAprovacao,tipoAtividadeRealizacao, atividadeRealizacao);
    }

    public FluxoAtividadesServico withApprovalActivity(AtividadeAprovacao atividadeAprovacao){
        this.atividadeAprovacao = atividadeAprovacao;
        return this;
    }

    public FluxoAtividadesServico withRealizationActivity(TipoAtividadeRealizacao tipoAtividadeRealizacao,AtividadeRealizacao atividadeRealizacao){
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
        this.atividadeRealizacao = atividadeRealizacao;
        return this;
    }

    public AtividadeAprovacao atividadeAprovacao() {
        return this.atividadeAprovacao;
    }

    public AtividadeRealizacao atividadeRealizacao() {
        return this.atividadeRealizacao;
    }

    public TipoAtividadeRealizacao getTipoAtividadeRealizacao() {
        return tipoAtividadeRealizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FluxoAtividadesServico that = (FluxoAtividadesServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Flux Service ID:"+this.id);
        if(atividadeAprovacao!=null) stringBuilder.append("\nHave Aproval Activity: True");
        stringBuilder.append("\nType of Realization Activity:"+tipoAtividadeRealizacao.toString());


        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
