package daemon.motorfluxo.algorithms.atribuicaoatividade;

import eapli.base.Application;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class AlgorithmColaboratorFactory {

    private static final String FCFS = "fcfs";
    private static final String WORKLOAD_BASED = "workloadbased";

    private AlgorithmColaboratorFactory(){}

    public static ActivityQueue create(){
        String algorithm = Application.settings().getColaboratorAlgorithm();
        if (algorithm == null) {
            System.out.println("No algorithm defined in properties! Assuming FCFS");
            return new FCFSActivityQueue();
        }
        switch (algorithm){
            case FCFS:
                return new FCFSActivityQueue();
            case WORKLOAD_BASED:
                return new LoadBasedActivityQueue();
            default:
                System.out.println("Invalid algorithm defined in properties! Assuming FCFS");
                return new FCFSActivityQueue();
        }
    }


}
