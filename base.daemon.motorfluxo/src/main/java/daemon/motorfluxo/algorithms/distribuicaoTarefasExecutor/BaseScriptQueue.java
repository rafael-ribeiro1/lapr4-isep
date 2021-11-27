package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public abstract class BaseScriptQueue {
    //ticketId,Script
    protected final Queue<Pair<String,String>> scriptsQueue;

    public BaseScriptQueue() {
        this.scriptsQueue =new ConcurrentLinkedQueue<> ();
    }

    public abstract Pair<String,String> pollScript();

    public synchronized void offerScript(final String ticketId, final String script){
        //add script to the queue
        scriptsQueue.offer (ImmutablePair.of ( ticketId,script ));
        ExecutorsThreadsData.putData( ticketId, 0 );
        //System.out.println ("script added:"+ticketId);
        notifyAll ();
    }
}
