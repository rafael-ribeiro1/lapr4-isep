package daemon.motorfluxo.application;

import eapli.base.activity.domain.Atividade;
import eapli.base.activity.domain.EstadoAtividade;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.repository.PedidoRepository;

public class InitiateFluxControllerImpl implements InitiateFluxController{
    private PedidoRepository ticketRepository= PersistenceContext.repositories().pedidos();
    @Override
    public FluxoAtividadesPedido getActivityFluxByTicketId(final String id) {
        return ticketRepository.getActivityFluxByTicketId(id);
    }
    @Override
    public void updateActivityFluxState(final String id, EstadoPedido state){
        ticketRepository.updateTicketState(id, state);
    }

    @Override
    public void updateActivityState(Atividade atividade, EstadoAtividade estadoAtividade) {
        ticketRepository.updateActivityState(atividade,estadoAtividade);
    }

    @Override
    public Pedido getPedido(String pedidoId) {
        return ticketRepository.ofIdentity(pedidoId).get();
    }

    @Override
    public void savePedido(Pedido pedidoToSave) {
        ticketRepository.save(pedidoToSave);
    }
}
