package daemon.motorfluxo.protocol;

import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import org.junit.Ignore;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_AVANCAR_FLUXO_PEDIDO;

@Ignore
public class FluxEngineExecutorIntegrationTest {
    //engine
    private final String engineIp="10.8.0.82";
    private final int enginePort=31942;
    //executor
    private final String executorIp="10.8.0.81";
    private final int executorPort = 31942;
    private final String cert="server_exec.jks";
    private final int version=1;
    private final int scriptExecCode=CODIGO_AVANCAR_FLUXO_PEDIDO;

    @Test
    public void testeExecucaoScriptPeloMotor1() {
        String taskId="2021/00001";
        SDP2021Packet p=new SDP2021Packet ( version,scriptExecCode, taskId);
        try{
            SDP2021Packet result=SDP2021Client.simpleCommunication ( p,engineIp,enginePort, cert);
            System.out.println (result.response ());
        }catch (IOException e){
            System.out.println ("(testeExecucaoScriptPeloMotor1)Erro");
            e.printStackTrace ();
        }
    }

    @Test
    public void testeExecucaoScriptPeloMotor2(){
        int nScripts=5;
        String ticketPrefix="2021/00";
        SDP2021Packet[]packets=new SDP2021Packet[nScripts];
        for (int i = 0; i < nScripts; i++) {
            String ticket=ticketPrefix;
            if(i<9)
                ticket=ticket+"00"+(i+1);
            else
                ticket=ticket+"0"+(i+1);
            packets[i]=new SDP2021Packet ( version,scriptExecCode,ticket );
        }

        List<Thread> l=new ArrayList<> (nScripts);

        for (SDP2021Packet packet : packets) {
           l.add(new Thread (()->{
                try{
                    SDP2021Packet result=SDP2021Client.simpleCommunication ( packet,engineIp,enginePort, cert );
                    System.out.println ("packet: "+packet+":"+result.response ());
                }catch (IOException e){System.out.println ("Erro "+packet);}
            }));
        }
        l.forEach ( Thread::start );

        for (Thread thread : l) {
            try {
                thread.join ();
            } catch (InterruptedException e) {
                System.out.println ("Thread "+thread.getId ()+" interrupted");
            }
        }
    }


    @Test
    public void testeServidor() throws IOException {
        SDP2021Packet p=new SDP2021Packet ( 0, CODIGO_AVANCAR_FLUXO_PEDIDO,"2021/00001");
        try{
            System.out.println(SDP2021Client.simpleCommunication ( p,engineIp,enginePort, "client_mf").response());
        }catch (IOException e){
            System.out.println ("Erro no teste");
        }
    }

    @Test
    public void testeExecutor() throws IOException {
        SDP2021Packet p=new SDP2021Packet ( 0,scriptExecCode,"Script exemplo" );
        try{
            System.out.println(SDP2021Client.simpleCommunication ( p,executorIp, executorPort, "client_exec").response());
        }catch (IOException e){
            System.out.println ("Erro no teste");
        }
    }

    @Test
    public void avancarMotorFluxo() throws IOException {
        SDP2021Packet p=new SDP2021Packet ( 1, CODIGO_AVANCAR_FLUXO_PEDIDO,"2021/00009" );
        try{
            SDP2021Packet packet = SDP2021Client.simpleCommunication ( p,engineIp,enginePort, "client_mf");
            String a = packet.response();


            System.out.println(a);
        }catch (IOException e){
            System.out.println ("Erro no teste");
        }

    }
}
