package daemon.motorfluxo.presentation;
import daemon.motorfluxo.algorithms.atribuicaoatividade.AtribuicaoAtividadeAlgorithm;
import daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.ExecutorsThreadsData;
import eapli.base.Application;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.utils.ANSI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;


public class MotorFluxoServer {
    private static final Logger LOGGER = LogManager.getLogger(MotorFluxoServer.class);
    private static final  int NUM_PORT = Application.settings().getEngineServerPort();

    //private static ServerSocket serverSocket;
    private static SSLServerSocket serverSocket;
    public  static AtribuicaoAtividadeAlgorithm algorithmColaborador;
    private static String TRUSTED_STORE="server_engine.jks";
    private static String KEYSTORE_PASS="forgotten";

    @SuppressWarnings ( "InfiniteLoopStatement" )
    public static void startServer() throws Exception {
        ///*
        /*//Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);
        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);*/

        SSLServerSocketFactory ssLF = getSocketFactory(TRUSTED_STORE, KEYSTORE_PASS);
        //SSLServerSocketFactory ssLF=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            serverSocket=(SSLServerSocket) ssLF.createServerSocket(NUM_PORT);
            serverSocket.setNeedClientAuth(true);
            //serverSocket=new ServerSocket (NUM_PORT);
        }catch(IOException ex) {
            ANSI.print ( "Local port "+NUM_PORT+ " number not available.",ANSI.YELLOW );
            LOGGER.info ( ANSI.format ("Local port "+NUM_PORT+ " number not available.",ANSI.YELLOW) );
            System.exit(1);
        }

        algorithmColaborador = new AtribuicaoAtividadeAlgorithm((List<Colaborador>) PersistenceContext.repositories().colaboradores().findAll());

        //iniciar N threads do executor
        ExecutorsThreadsData.initExecutorThreads ();
        while(true) {
            Socket s=serverSocket.accept(); // wait for a new client connection request
            new HandlerClient(s).start ();
        }
    }

    private static SSLServerSocketFactory getSocketFactory(String certName, String password) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream trustStore = new FileInputStream(certName);
            keyStore.load(trustStore, password.toCharArray());
            trustStore.close();
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, password.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext ctx;
            try {
                ctx = SSLContext.getInstance("TLS");
            } catch (NoSuchAlgorithmException e) {
                ctx = SSLContext.getInstance("SSL");
            }
            ctx.init(keyManagerFactory.getKeyManagers(), tmf.getTrustManagers(), null);
            return ctx.getServerSocketFactory();
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
            throw new IOException("Error defining KeyStore");
        }
    }

}

class HandlerClient extends Thread {

    private final Socket clientSocket;
    private static final Logger LOGGER = LogManager.getLogger(HandlerClient.class);

    public HandlerClient(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            ANSI.log ( LOGGER::info,"Client initialized", ANSI.GREEN);
            ANSI.print ( "Client initialized", ANSI.GREEN );
            // Ler request do client
            SDP2021Packet request = SDP2021Packet.readPacket(inputStream);
            // Parser faz tratamento do request e gera resposta
            SDP2021Packet response = MotorFluxoParser.parse(request);
            // Enviar resposta para o client
            response.sendPacket(outputStream);
            clientSocket.close();
        } catch (IOException e) {
            ANSI.log ( LOGGER::error, "Error while connecting to the server",ANSI.RED );
        }
    }
}
