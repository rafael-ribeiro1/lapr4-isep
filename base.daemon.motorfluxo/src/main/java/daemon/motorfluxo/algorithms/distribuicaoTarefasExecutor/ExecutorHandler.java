package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import eapli.base.Application;
import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;
import eapli.base.utils.ANSI;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ExecutorHandler implements Runnable{
    private static final Logger LOGGER = LogManager.getLogger(ExecutorHandler.class);
    private final BaseScriptQueue q;
    private final int version;
    private final ConnectionPair connectionPair;
    private final String cert;

    public ExecutorHandler(final BaseScriptQueue q,  final int version,ConnectionPair connectionPair) {
        this.q = q;
        this.version = version;
        this.connectionPair=connectionPair;
        this.cert=Application.settings().getCertClientName();

    }

    @Override
    @SuppressWarnings ( "InfiniteLoopStatement" )
    public void run() {
        while (true) {
            handle ();
        }
    }

    private void handle(){
        long threadId = Thread.currentThread ().getId ();
        logInfo ( "Thread " + threadId + " waiting for script" );
        Pair<String,String> ticketIdScript=q.pollScript ();
        ExecutorsThreadsData.incrementConnectionPairCounter ( connectionPair );
        //ExecutorsThreadsData.incrementConnectionPairCounter ( connectionPair );
        String ticketId=ticketIdScript.getLeft ();
        //logInfo ( "Thread " + threadId + " obtains script from request " + ticketId );
        Semaphore sem=getSemaphore (ticketId);
        //System.out.println ( "(Thread " + threadId + ")got the thread semaphore" );
        SDP2021Packet request= createRequest ( ticketIdScript.getRight () );
        try {
            SDP2021Packet executorResponse= sendRequestAndGetResponse (request);
            ExecutorsThreadsData.decrementConnectionPairCounter ( connectionPair );
            //acquire the semaphore to write in packet in the shared memory. It will be released in the thread reading from the packet
            ExecutorsThreadsData.addTicketExecutionCode ( ticketId,executorResponse.code () );
            logInfo ( "Thread " + threadId + " wrote to packet" );
            //inform the thread that invoked this thread that it can read from the packet in the shared memory
            sem.release ();
            System.out.println ( "(Thread " + threadId + ")released the thread semaphore" );
        } catch (IOException e) {
            logWarning ( "(Thread "+threadId+"): there was problem while connecting to the executor->"+executorDataToString () );
            e.printStackTrace ();
        }
    }


    private SDP2021Packet createRequest(final String script){
        return new SDP2021Packet (version, SDP2021ProtocolTypeMessages.CODIGO_SCRIPT,script);
    }

    private SDP2021Packet sendRequestAndGetResponse(SDP2021Packet request) throws IOException{
        return SDP2021Client.simpleCommunication (request, connectionPair.ip (), connectionPair.port (), cert);
    }

    private Semaphore getSemaphore(final String ticketId){
        Semaphore sem=ExecutorsThreadsData.getSemaphoreByTicketId ( ticketId );
        //create semaphore if there was a failure in creating while getting the script from the queue
        if(sem==null){
            ExecutorsThreadsData.putData ( ticketId,0 );
            return ExecutorsThreadsData.getSemaphoreByTicketId ( ticketId );
        }
        return sem;
    }

    private void logInfo(final String text){
        ANSI.log ( LOGGER::info, text,ANSI.YELLOW);
        ANSI.print ( text, ANSI.YELLOW);

    }

    private void logWarning(final String text){
        ANSI.log ( LOGGER::warn, text,ANSI.UNDERLINE_YELLOW);
        ANSI.print ( text,ANSI.UNDERLINE_YELLOW );
    }

    private String executorDataToString(){
       return "ip "+connectionPair.ip () +"; port "+connectionPair.port ();
    }
}
