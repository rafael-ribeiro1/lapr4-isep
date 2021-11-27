package eapli.base.activity.domain;
import eapli.base.colaboratormanagement.domain.Colaborador;

import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.servicemanagement.domain.form.Form;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;


@Entity
public class AtividadeAprovacao extends Atividade implements ValueObject {

    @Enumerated(EnumType.STRING)
    private TipoResponsavel tipoResponsavel;

    @ManyToOne
    private Colaborador responsavel;

    @OneToOne(cascade=CascadeType.ALL)
    private Form form;

    public AtividadeAprovacao(Long id, DataLimite dataLimite,Colaborador responsavel, TipoResponsavel tipoResponsavel,Form form ) {
        super(id, dataLimite);
        Preconditions.noneNull(responsavel,form,tipoResponsavel);
        this.responsavel = responsavel;
        this.form= form;
        this.tipoResponsavel = tipoResponsavel;
    }

    public AtividadeAprovacao(EstadoAtividade estado, DataLimite dataLimite, Colaborador responsavel,TipoResponsavel tipoResponsavel,Form form ) {
        super(estado, dataLimite);
        Preconditions.noneNull(responsavel, tipoResponsavel, form);
        this.responsavel = responsavel;
        this.form= form;
        this.tipoResponsavel = tipoResponsavel;
    }

    public AtividadeAprovacao( Colaborador responsavel,TipoResponsavel tipoResponsavel,Form form ) {
        Preconditions.noneNull(responsavel,tipoResponsavel,form);
        this.responsavel = responsavel;
        this.form= form;
        this.tipoResponsavel = tipoResponsavel;
    }

    public AtividadeAprovacao( String tipoResponsavel,Form form ) {
        //PARA SERVICO
        Preconditions.noneNull(tipoResponsavel,form);
        this.form= form;
        this.tipoResponsavel = TipoResponsavel.valueOf(tipoResponsavel);
    }

    public AtividadeAprovacao(EstadoAtividade estado, DataLimite dataLimite, TipoResponsavel tipo, Form form, Colaborador responsavel) {
        // PARA PEDIDO
        super(estado, dataLimite);
        this.tipoResponsavel = tipo;
        this.form = form;
        this.responsavel = responsavel;
    }

    protected AtividadeAprovacao() {
        super();
    }

    public Colaborador responsavel(){
        return this.responsavel;
    }

    public List<Attribute> attributes() {
        return form.attributes();
    }

    public Form form() {
        return this.form;
    }

    public TipoResponsavel tipoResponsavel() {
        return tipoResponsavel;
    }

    public AtividadeAprovacao cloneForRequest(Calendar dataLimite, Colaborador responsavel) {
        return new AtividadeAprovacao(EstadoAtividade.PENDENTE, new DataLimite(dataLimite), this.tipoResponsavel, this.form, responsavel);
    }

    @Override
    public String detailedInformation(){
        return String.format("%s%nType Activity:%s%n",super.detailedInformation(),"Approval Activity");
    }

    @Override
    public String toString() {
        return super.toString().concat(" | Approval Activity");
    }
}
