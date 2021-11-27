package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import eapli.base.utils.ANSI;
import org.apache.commons.lang3.tuple.Pair;

public class ScriptQueueLoadBased extends BaseScriptQueue{

    @Override
    public synchronized Pair<String, String> pollScript() {
        boolean isTurn=ExecutorsThreadsData.checkNextAvailableInstance();;
        //boolean isTurn=false;
        while(scriptsQueue.isEmpty() || !isTurn ){
            try{
                wait ();
            }catch (InterruptedException ignored){}
            //verificar a proxima instancia disponivel
            //ANSI.print ("Thread's "+Thread.currentThread ().getId ()+" checking turn",ANSI.UNDERLINE_GREEN);
            isTurn=ExecutorsThreadsData.checkNextAvailableInstance();
            //ANSI.print ("Thread's "+Thread.currentThread ().getId ()+" turn? "+isTurn,ANSI.UNDERLINE_YELLOW);
        }
        //System.out.println ();
        Pair<String,String> ticketIdScriptPair=scriptsQueue.poll ();
        notifyAll ();
        return ticketIdScriptPair;
    }
}
