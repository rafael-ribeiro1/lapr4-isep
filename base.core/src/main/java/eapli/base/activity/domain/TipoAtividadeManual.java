package eapli.base.activity.domain;

public enum TipoAtividadeManual {
    APROVACAO("Aprovacao"),
    REALIZACAO("Realizacao");

    String tipoAtividade;

    TipoAtividadeManual(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }
}
