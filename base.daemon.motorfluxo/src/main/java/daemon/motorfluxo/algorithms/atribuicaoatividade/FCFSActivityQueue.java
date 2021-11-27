package daemon.motorfluxo.algorithms.atribuicaoatividade;

import eapli.base.activity.domain.RealizacaoManual;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidoservico.domain.Pedido;

public class FCFSActivityQueue extends ActivityQueue {

    @Override
    public synchronized void handle(Colaborador colab) {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Pedido pedido = queue.peek();
        if(pedido != null && pedido.verifyAccessColaborator(colab)){
            RealizacaoManual atividadeManual = (RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao();
            atividadeManual.addResponsavel(colab);
            PersistenceContext.repositories().pedidos().save(pedido);
            queue.poll();
        }
        if (!queue.isEmpty())
            sem.release();
    }

}
