package daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor;

import eapli.base.Application;
import eapli.base.utils.ANSI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


public class ExecutorsThreadsData {
    private static final Logger LOGGER = LogManager.getLogger( ExecutorsThreadsData.class);
    private static BaseScriptQueue q;
    //TicketId, Semaphore
    private static final Map<String, Semaphore> tickedIdSemaphore;
    //Server(ip/porta), num de instancias
    private static final Map<ConnectionPair,Integer> connectionModeInstancesMap;
    //thread por ip/porta
    private static final Map<Long, ConnectionPair> threadIdByConnectionModeMap;

    private static final Map<String,Integer> ticketExecutionCode;


    static{
        tickedIdSemaphore =new HashMap<> ();
        //new things
        connectionModeInstancesMap=new HashMap<> ();
        threadIdByConnectionModeMap=new ConcurrentHashMap<> ();
        ticketExecutionCode =new HashMap<> ();
    }

    public static synchronized void addTicketExecutionCode(final String ticket, final int response){
        ticketExecutionCode.put (ticket, response);
    }

    public static synchronized int getTicketExecutionCode(final String ticket){
        return ticketExecutionCode.get ( ticket );
    }

    public static synchronized void initExecutorThreads(){
        int nThreads=Application.settings ().getEngineExecutorThreads ();
        String[]ips=Application.settings ().getExecutorsServerIps ();
        int[]ports=Application.settings ().getExecutorServerPortList ();
        ANSI.print ( "Executor ips",ANSI.YELLOW );
        for (String ip : ips) {
            System.out.println (ip);
        }
        ANSI.print ( "Executor ports",ANSI.YELLOW );
        for (int p:ports) {
            System.out.println (p);
        }
        q=AlgorithmFactory.create();
        System.out.println ("Running "+q.getClass ().getSimpleName ());
        int version=Application.settings ().getSdpProtocolVersion ();
        boolean portMode=Application.settings ().getConnectionMode ();
        int len=determineNumberOfConnectableInstances(ips,ports, portMode);
        ANSI.print ("Initiating connections with executor in "+(portMode ? "port mode":"ip mode"),ANSI.CYAN);
        for (int i = 0; i < nThreads; i++) {
            int idx=i% len;
            String ip;
            int port;
            if(portMode){
                ip=ips[0];
                port=ports[idx];
            }else{
                ip=ips[idx];
                port=ports[0];
            }
            ConnectionPair c=new ConnectionPair (  ip,port );
            Thread t = new Thread ( new ExecutorHandler ( q, version, c ) );
            connectionModeInstancesMap.put ( c,0 );
            threadIdByConnectionModeMap.put ( t.getId (),c );

            t.start ();
            ANSI.print ( "Thread "+t.getId ()+" with executor port "+port+" initiated",ANSI.BOLD_HIGH_INTENSITY_GREEN );
            ANSI.log ( LOGGER::info, "Thread "+t.getId ()+" with executor port "+port+" initiated",ANSI.BOLD_HIGH_INTENSITY_GREEN);
        }
    }

    private static int determineNumberOfConnectableInstances(String[]ips, int[]ports, boolean portMode){
        if(portMode)
            return ports.length;
        else
            return ips.length;
    }

    public static void offerScriptToQueue(final String ticketId, final String script){
        q.offerScript ( ticketId,script );
    }

    public static synchronized Semaphore getSemaphoreByTicketId(final String id){
        return tickedIdSemaphore.get ( id );
    }

    public static synchronized void putData(final String ticketId, final Integer permits){
        tickedIdSemaphore.put ( ticketId,new Semaphore ( permits ) );
    }

    public static synchronized void putData(final String ticketId,  final Semaphore sem){
        tickedIdSemaphore.put ( ticketId, sem );
    }

    public static synchronized int removeData(final String ticketId){
        tickedIdSemaphore.remove ( ticketId );
        return ticketExecutionCode.remove ( ticketId );
    }

    public static synchronized void incrementConnectionPairCounter(ConnectionPair p){
        connectionModeInstancesMap.put(p,connectionModeInstancesMap.get ( p )+1);
    }

    public static synchronized void decrementConnectionPairCounter(ConnectionPair p){
        int numInstances = connectionModeInstancesMap.get (p);
        if (numInstances > 0)
            connectionModeInstancesMap.put (p,numInstances-1);
    }

    public static synchronized boolean checkNextAvailableInstance(){
        long id=Thread.currentThread().getId();
        System.out.println (id+"-entered the method");
        int min = Integer.MAX_VALUE;
        ConnectionPair p=null;
        int numInstances;
        for(Map.Entry<ConnectionPair, Integer> entry : connectionModeInstancesMap.entrySet()){
            numInstances = entry.getValue();
            System.out.println ( ">(Thread "+id+")"+entry.getKey ().port ()+":" +numInstances);
            if(numInstances==0){
                p= entry.getKey ();
                break;
            }
            if (numInstances < min ){
                min = numInstances;
                p = entry.getKey();
            }
        }
        System.out.println ("---(thread "+id+")Executor with less load: "+p.port ());
        return p.equals ( threadIdByConnectionModeMap.get ( id ) );
    }
}
