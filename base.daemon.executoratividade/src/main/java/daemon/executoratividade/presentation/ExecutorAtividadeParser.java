package daemon.executoratividade.presentation;

import daemon.executoratividade.protocol.ExecutarAtividadeRequestFactory;
import eapli.base.protocolo.domain.SDP2021Packet;
import daemon.executoratividade.protocol.BaseAtividadeRequest;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public class ExecutorAtividadeParser {

    public static SDP2021Packet parse(final SDP2021Packet inputPacket) {
        int version = inputPacket.version();
        String script = inputPacket.response();
        BaseAtividadeRequest request = ExecutarAtividadeRequestFactory.create(version, script);
        switch (inputPacket.code()) {
            case CODIGO_TESTE:
                return request.buildTestRequest();
            case CODIGO_FIM:
               return request.buildEntendidoRequest();
            case CODIGO_SCRIPT:
                return request.execute();
            default:
                return errorPacket(request.buildScriptErrorRequest(ERROR_MESSAGE_SCRIPT),version);
        }
    }

    private static SDP2021Packet errorPacket(String messageError , int version){
        return new SDP2021Packet(version,CODIGO_ERRO,messageError);
    }

    private static SDP2021Packet errorPacket( int version){
        return new SDP2021Packet(version,CODIGO_ERRO,ERROR_MESSAGE);
    }
}
