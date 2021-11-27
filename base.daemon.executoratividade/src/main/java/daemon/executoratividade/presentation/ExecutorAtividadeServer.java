package daemon.executoratividade.presentation;

import eapli.base.Application;
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


public class ExecutorAtividadeServer {

    private static final Logger LOGGER = LogManager.getLogger(ExecutorAtividadeServer.class);
    private static final  int NUM_PORT = Application.settings().getExecutorServerPort();
    private static final  int[] PORTS_ARR=Application.settings().getExecutorServerPortList ();

    static final String TRUSTED_STORE="server_exec.jks";
    static final String KEYSTORE_PASS="forgotten";

    private static SSLServerSocket serverSocket;

    public static void startServer() throws Exception {
///*
        /*// Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);*/

        // Necessário por causa de conflitos com API de email
        SSLServerSocketFactory sslF = getSocketFactory(TRUSTED_STORE, KEYSTORE_PASS);
        //SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        for (int port : PORTS_ARR) {
            try {
                serverSocket = (SSLServerSocket) sslF.createServerSocket(port);
                serverSocket.setNeedClientAuth(true);
                //LOGGER.info("Starting the server socket");
                System.out.println("Starting the server socket");
                break;
            }catch(IOException ex) {
                System.out.printf("Local port number %d not available.",port);
                //System.exit(1);
            }
        }
        while(true) {
            Socket s=serverSocket.accept(); // wait for a new client connection request
            new ClientHandler(s).start();
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

class ClientHandler extends Thread{

    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            //LOGGER.info("Client initialized");
            System.out.println("Client initialized");

            // Ler request do client
            SDP2021Packet request = SDP2021Packet.readPacket(inputStream);
            ANSI.print("Recebeu o script", ANSI.CYAN);

            // Parser faz tratamento do request e gera resposta
            SDP2021Packet response = ExecutorAtividadeParser.parse(request);
            ANSI.print("Terminou a execução do script", ANSI.CYAN);
            // Enviar resposta para o client
            response.sendPacket(outputStream);

            clientSocket.close();
        } catch (IOException e) {
            LOGGER.error("Error while connecting to the server");
            System.out.println("Error while connecting to the server");
        }
    }

}
