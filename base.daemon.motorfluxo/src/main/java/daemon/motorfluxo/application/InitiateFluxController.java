package daemon.motorfluxo.application;

import eapli.base.activity.domain.Atividade;
import eapli.base.activity.domain.EstadoAtividade;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;

public interface InitiateFluxController {
    FluxoAtividadesPedido getActivityFluxByTicketId(final String id);
    void updateActivityFluxState(final String id, EstadoPedido state);

    void updateActivityState(final Atividade atividade, EstadoAtividade estadoAtividade);

    Pedido getPedido (String pedidoId);

    void savePedido(Pedido pedidoToSave);
}
