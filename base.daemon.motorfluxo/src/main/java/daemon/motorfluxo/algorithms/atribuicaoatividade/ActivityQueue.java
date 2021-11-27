package daemon.motorfluxo.algorithms.atribuicaoatividade;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.pedidoservico.domain.Pedido;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public abstract class ActivityQueue {

    protected final Queue<Pedido> queue = new ConcurrentLinkedQueue<>();

    protected final Semaphore sem = new Semaphore(0, true);

    public boolean addActivity(Pedido pedido) {
        if (queue.offer(pedido)) {
            sem.release();
            return true;
        }
        return false;
    }

    public abstract void handle(Colaborador colab);

}
