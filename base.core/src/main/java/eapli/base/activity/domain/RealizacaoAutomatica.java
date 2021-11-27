package eapli.base.activity.domain;

import eapli.base.antlr.linguagemScripts.AutomatedScriptAPI;
import eapli.base.antlr.linguagemScripts.AutomatedScriptListenerAPI;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class RealizacaoAutomatica extends AtividadeRealizacao  implements ValueObject {

    @Embedded
    private Script script;

    public RealizacaoAutomatica(Long id, DataLimite dataLimite) {
        super(id, dataLimite, TipoAtividadeRealizacao.AUTOMATICA);
    }

    public RealizacaoAutomatica(EstadoAtividade estado, DataLimite dataLimite, Script script)  {
        super(estado, dataLimite, TipoAtividadeRealizacao.AUTOMATICA);
        Preconditions.nonNull(script);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(script.toString());
        if (!api.validateScript())
            throw new IllegalArgumentException("Invalid Automatic Script");
        this.script = script;
    }

    public RealizacaoAutomatica(Script script) {
        super(TipoAtividadeRealizacao.AUTOMATICA);
        Preconditions.nonNull(script);
        AutomatedScriptAPI api = new AutomatedScriptListenerAPI(script.toString());
        if (!api.validateScript())
            throw new IllegalArgumentException("Invalid Automatic Script");
        this.script = script;
    }

    protected RealizacaoAutomatica() {
        //ORM
    }
    public String getScriptInStringFormat(){
        return script.toString ();
    }
/*
    public byte[] scriptToByteArray() {
        return this.script.toByteArray ();
    }

    public String scriptToByteArrayInStringFormat() {
        byte[] arr=this.scriptToByteArray();
        StringBuilder s=new StringBuilder ();
        for (byte b : arr) {
            s.append ( b );
        }
        return s.toString ();
    }
*/
    @Override
    public AtividadeRealizacao cloneForRequest(Calendar dataLimite) {
        return new RealizacaoAutomatica(EstadoAtividade.PENDENTE, new DataLimite(dataLimite), this.script);
    }
}
