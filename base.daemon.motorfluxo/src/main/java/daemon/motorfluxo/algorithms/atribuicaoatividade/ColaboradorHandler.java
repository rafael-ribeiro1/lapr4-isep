package daemon.motorfluxo.algorithms.atribuicaoatividade;


import eapli.base.colaboratormanagement.domain.Colaborador;

public class ColaboradorHandler extends Thread {

    private ActivityQueue queue;
    private Colaborador colab;



    public ColaboradorHandler(Colaborador colab,ActivityQueue que) {
        this.colab = colab;
        this.queue=que;
    }

    @Override
    public void run() {
        while (true) {
            queue.handle(this.colab);
            try {
                Thread.sleep(1); // 1ms para contornar escalonador
            } catch (InterruptedException e) {}
        }
    }

}
