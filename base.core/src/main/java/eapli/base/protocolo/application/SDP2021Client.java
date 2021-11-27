package eapli.base.protocolo.application;

import eapli.base.protocolo.domain.SDP2021Packet;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class SDP2021Client {

    private static final String KEYSTORE_PASS="forgotten";

    public static SDP2021Packet simpleCommunication(SDP2021Packet data, String ipAddress, int port, String certName) throws IOException {
        // Trust these certificates provided by servers
        /*
        System.setProperty("javax.net.ssl.trustStore", certName + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", certName + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);
        */

        // Foi necessário adaptar desta forma, devido a conflitos com a API de envio de email
        SSLSocketFactory sf = getSocketFactory(certName, KEYSTORE_PASS);
        //SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        InetAddress serverIP = InetAddress.getByName(ipAddress);
        SDP2021Packet response;
        //try (Socket socket = new Socket (serverIP, port)) {
        try (SSLSocket socket = (SSLSocket) sf.createSocket(serverIP, port)) { // Close automático
            socket.startHandshake();

            DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream sIn = new DataInputStream(socket.getInputStream());

            // Enviar dados
            data.sendPacket(sOut);

            // Ler resposta do server
            response = SDP2021Packet.readPacket(sIn);
        }
        return response;
    }

    private static SSLSocketFactory getSocketFactory(String certName, String password) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream trustStore = new FileInputStream(certName + ".jks");
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
            return ctx.getSocketFactory();
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
            throw new IOException("Error defining KeyStore");
        }
    }

}
