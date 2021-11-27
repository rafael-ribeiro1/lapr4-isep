package eapli.base.activity.domain;

public enum TipoAtividadeRealizacao {
    MANUAL("Manual"),
    AUTOMATICA("Automatic");

    String tipoAtividade;

    TipoAtividadeRealizacao(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }
}
