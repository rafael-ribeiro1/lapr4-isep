package eapli.base.pedidoservico.domain;

import eapli.base.activity.domain.*;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.servicemanagement.domain.form.DataType;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
public class FluxoAtividadesPedido implements ValueObject {


    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private AtividadeAprovacao atividadeAprovacao;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private FormResponses formAprovacao;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private AtividadeRealizacao atividadeRealizacao;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private FormResponses formRealizacao;

    @Enumerated(value = EnumType.STRING)
    private TipoAtividadeRealizacao tipoAtividadeRealizacao;

    protected FluxoAtividadesPedido() {}

    public FluxoAtividadesPedido(AtividadeAprovacao aprovacao, TipoAtividadeRealizacao tipoAtividadeRealizacao,
                                 AtividadeRealizacao realizacao) {
        Preconditions.noneNull(tipoAtividadeRealizacao, realizacao);
        this.atividadeAprovacao = aprovacao;
        this.tipoAtividadeRealizacao = tipoAtividadeRealizacao;
        this.atividadeRealizacao = realizacao;
    }

    public AtividadeAprovacao atividadeAprovacao() {
        return atividadeAprovacao;
    }

    public AtividadeRealizacao atividadeRealizacao() {
        return atividadeRealizacao;
    }

    public TipoAtividadeRealizacao tipoAtividadeRealizacao() {
        return tipoAtividadeRealizacao;
    }

    public boolean hasAtividadeAprovacao(){
        return this.atividadeAprovacao!=null;
    }

    public boolean isRealizacaoAutomatica(){
        return tipoAtividadeRealizacao== TipoAtividadeRealizacao.AUTOMATICA;
    }

    public boolean atualizarFluxoAtividadePendente(Colaborador utilizador) {
        return ((RealizacaoManual)atividadeRealizacao).reivindicarAtividadePendente(utilizador);
    }

    public boolean resultadoAprovacao(){
        String response = formAprovacao.obtainResponse(Attribute.create("Decision", "Attribute containing approval decision",
                "decision", DataType.BOOLEAN.toString(),"(true)|(false)"));
        return Boolean.parseBoolean(response);
    }

    public Long identifier() {
        return id;
    }

    public void saveResponsesOfActivity(TipoAtividadeManual tipoAtividade, Map<Attribute, String> formResponses) {
        if (tipoAtividade == TipoAtividadeManual.APROVACAO) {
            if (atividadeAprovacao == null)
                throw new IllegalArgumentException("This request doesn't have approval activity");
            formAprovacao = new FormResponses(formResponses);
        } else if (tipoAtividade == TipoAtividadeManual.REALIZACAO) {
            formRealizacao = new FormResponses(formResponses);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FluxoAtividadesPedido that = (FluxoAtividadesPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
