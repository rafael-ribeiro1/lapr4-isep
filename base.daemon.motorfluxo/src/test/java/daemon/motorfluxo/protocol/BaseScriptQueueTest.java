package daemon.motorfluxo.protocol;

import daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.BaseScriptQueue;
import daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.ScriptQueueFCFS;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;


public class BaseScriptQueueTest {
    @Rule
    public TestName testName=new TestName ();
    private BaseScriptQueue queue;

    public BaseScriptQueueTest() {
        this.queue = new ScriptQueueFCFS();
    }

    @Before
    public void setUp() throws Exception {
        if(testName.getMethodName ()
                .equals ( "correctlyPollScriptInMultiThread" ))
            return;
        String ticketId1="id1";
        String script1="script1";
        queue.offerScript ( ticketId1,script1 );
        String ticketId2="id2";
        String script2="script2";
        queue.offerScript ( ticketId2,script2 );
        String ticketId3="id3";
        String script3="script3";
        queue.offerScript ( ticketId3,script3 );
    }
    @Test
    public void correctlyPollScriptInMultiThread(){
        String ticketId1="id1";
        String script1="script1";
        Pair<String, String> expected=Pair.of ( ticketId1,script1 );
        //waits for a script to be added and then polls from queue
        new Thread ( ()->{
            Pair<String, String> result=queue.pollScript ();
            assertEquals ( expected.getLeft (),result.getLeft () );
            assertEquals ( expected.getRight (),result.getRight () );
        }).start ();
        //add script
        new Thread ( ()->queue.offerScript ( ticketId1,script1 )).start ();
    }

    @Test
    public void correctlyPollScript(){
        Pair<String,String> result=queue.pollScript ();
        assertEquals ( "id1",result.getLeft ());
        assertEquals ( "script1",result.getRight ());
    }
}