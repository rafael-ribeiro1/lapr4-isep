package eapli.base.activity.domain;



public enum EstadoAtividade  {
    PENDENTE("Pending"),
    PRONTO_EXECUTAR("Ready To Execute"),
    EM_PROCESSO("In Progress"),
    CONCLUIDA("Completed"),
    CANCELADA ("Canceled");

    String estado;

    EstadoAtividade(String estado) {
        this.estado = estado;
    }
}
