package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import org.apache.commons.lang3.tuple.Pair;

public class ScriptQueueFCFS extends BaseScriptQueue {

    public synchronized Pair<String,String> pollScript(){
        while(scriptsQueue.isEmpty ()){
            try{
                wait ();
            }catch (InterruptedException ignored){}
        }
        Pair<String,String> ticketIdScriptPair=scriptsQueue.poll ();
        notifyAll ();
        return ticketIdScriptPair;
    }
}
