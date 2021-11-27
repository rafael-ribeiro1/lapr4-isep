package eapli.base.activity.domain;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.servicemanagement.domain.form.Form;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity

public class RealizacaoManual extends AtividadeRealizacao implements ValueObject {

    @ManyToOne
    private Colaborador responsavel;

    @ManyToMany
    private List<Equipa> equipas;

    @OneToOne(cascade=CascadeType.ALL)
    private Form form;


    public RealizacaoManual(EstadoAtividade estado, DataLimite dataLimite, Colaborador responsavel,Form form) {
        super(estado, dataLimite, TipoAtividadeRealizacao.MANUAL);
        Preconditions.noneNull(responsavel, form);
        this.responsavel = responsavel;
        this.form=form;
    }

    public RealizacaoManual(EstadoAtividade estado, DataLimite dataLimite, List<Equipa> equipas, Form form) {
        super(estado, dataLimite, TipoAtividadeRealizacao.MANUAL);
        Preconditions.noneNull(equipas, form);
        //Preconditions.nonEmpty(equipas, "At least one team must be associated");
        this.equipas = equipas;
        this.form=form;
    }

    public RealizacaoManual(Long id, DataLimite dataLimite,Colaborador responsavel,Form form) {
        super(id, dataLimite, TipoAtividadeRealizacao.MANUAL);
        Preconditions.noneNull(responsavel, form);
        this.responsavel = responsavel;
        this.form=form;
    }
    public RealizacaoManual(Colaborador responsavel,Form form ) {
        super(TipoAtividadeRealizacao.MANUAL);
        Preconditions.nonNull(form);
        this.form=form;
        this.responsavel = responsavel;
    }

    public RealizacaoManual(List<Equipa> team , Form form){
        super(TipoAtividadeRealizacao.MANUAL);
        Preconditions.noneNull(form,team);
        Preconditions.nonEmpty(team, "At least one team must be associated");
        this.form=form;
        this.equipas =team;
    }

    protected RealizacaoManual() { }

    @Override
    public AtividadeRealizacao cloneForRequest(Calendar dataLimite) {
        if (equipas != null)
            return new RealizacaoManual(EstadoAtividade.PENDENTE, new DataLimite(dataLimite), new ArrayList<>(this.equipas), this.form);
        else
            return new RealizacaoManual(EstadoAtividade.PENDENTE ,new DataLimite(dataLimite),this.responsavel, this.form);
    }

    public List<Equipa> equipas() {
        return equipas;
    }

    public List<Attribute> attributes() {
        return form.attributes();
    }

    public Form form() {
        return this.form;
    }

    @Override
    public String detailedInformation(){
        return String.format("%s%nType Activity:%s%n",super.detailedInformation(),"Manual Realization");
    }

    public boolean reivindicarAtividadePendente(Colaborador utilizador){
        for (Equipa e : equipas) {
            if (e.colaboradores().contains(utilizador) || e.responsaveis().contains(utilizador)) {
                this.addResponsavel(utilizador);
                return true;
            }
        }
        return false;
    }

    public void addResponsavel(Colaborador responsavel) {
        this.responsavel = responsavel;
    }


}
