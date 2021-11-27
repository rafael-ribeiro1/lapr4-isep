package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import eapli.base.Application;

public class AlgorithmFactory {
    private static final String FCFS = "fcfs";
    private static final String LOAD_BASED = "loadbased";

    private AlgorithmFactory(){
        //empty
    }

    public static BaseScriptQueue create(){
        String algorithm = Application.settings().getTaskAlgorithm();
        algorithm = algorithm.toLowerCase();
        switch(algorithm){
            case LOAD_BASED:
                return new ScriptQueueLoadBased();
            case FCFS:
            default:
                return new ScriptQueueFCFS();
        }
    }
}
