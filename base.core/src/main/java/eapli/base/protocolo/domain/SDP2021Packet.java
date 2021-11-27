package eapli.base.protocolo.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SDP2021Packet {

    private int version;

    private int code;

    private String response;

    private boolean closeConnection;

    public SDP2021Packet(int version, int code, String response) {
        this.version = version;
        this.code = code;
        this.response = response;
        closeConnection=false;
    }

    public SDP2021Packet(int version, int code, String response,boolean toClose) {
        this.version = version;
        this.code = code;
        this.response = response;
        closeConnection=toClose;
    }

    public SDP2021Packet(final SDP2021Packet packet){
        this.version = packet.version;
        this.code = packet.code;
        this.response = packet.response;
        this.closeConnection=packet.closeConnection;
    }

    public boolean isCloseConnection() {
        return closeConnection;
    }

    public int version() {
        return version;
    }

    public int code() {
        return code;
    }

    public String response() {
        return response;
    }

    public void defineCode(int code) {
        this.code = code;
    }

    public void defineResponse(String response) {
        this.response = response;
    }

    public static SDP2021Packet readPacket(DataInputStream inputStream) throws IOException {
        int version;
        int code;
        int length;
        byte[] info;
        List<Byte> totalInfo = new ArrayList<>();
        do {
            version =  Byte.toUnsignedInt(inputStream.readByte());
            code = Byte.toUnsignedInt(inputStream.readByte());
            length = Byte.toUnsignedInt(inputStream.readByte());
            info = inputStream.readNBytes(length);
            addBytes(totalInfo, info);
        } while (code == 255);
        String data = bytesToString(totalInfo);
        return new SDP2021Packet(version, code, data);
    }

    public void sendPacket(DataOutputStream outputStream) throws IOException {
        byte[] data = this.response.getBytes();
        int bytes=data.length;
        int numPackets=(int)Math.ceil (data.length/(double)255);
        int i=0;
        for (;i<numPackets-1;i++) {
            outputStream.write((byte)this.version); // VERSAO DO SDP
            outputStream.write((byte)255); // mandar o codigo de segmento
            int range=i*255;
            outputStream.write((byte)255); // mandar tamanho
            outputStream.write(data,range,255); // mandar o objeto em si
            bytes-=255;
        }
        int range=i*255;
        // enviar packet final
        outputStream.write((byte)this.version); // VERSAO DO SDP
        outputStream.write((byte)this.code); // mandar o codigo
        outputStream.write((byte)bytes); // mandar tamanho
        outputStream.write(data,range,bytes); // mandar o objeto em si
    }

    private static void addBytes(List<Byte> l, byte[] info) {
        for (byte b : info) {
            l.add(b);
        }
    }

    private static String bytesToString(List<Byte> l) {
        byte[] arr = new byte[l.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = l.get(i);
        }
        return new String(arr, 0, l.size());
    }

    @Override
    public String toString() {
        return "SDP2021Packet{" +
                "version=" + version +
                ", code=" + code +
                ", response='" + response + '\'' +
                ", closeConnection=" + closeConnection +
                '}';
    }
}
