package daemon.motorfluxo.algorithms.atribuicaoatividade;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.pedidoservico.domain.Pedido;

import java.util.List;

public class AtribuicaoAtividadeAlgorithm {

    private final ActivityQueue queue = AlgorithmColaboratorFactory.create();

    public AtribuicaoAtividadeAlgorithm(List<Colaborador> colaboradores) {
        for (Colaborador colab : colaboradores) {
            createCollaborator(colab);
        }
    }

    public void createCollaborator(Colaborador colaborador) {
        ColaboradorHandler colab = new ColaboradorHandler(colaborador,queue);
        colab.start();
    }

    public synchronized boolean addAtividade(Pedido pedido) {
        return queue.addActivity(pedido);
    }
}
