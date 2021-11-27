package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;

public class TesteRequest extends BaseInformacaoRequest {

    public TesteRequest(String script) {
        super(script);
    }

    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(VERSION, SDP2021ProtocolTypeMessages.CODIGO_TESTE, SDP2021ProtocolTypeMessages.TEST_MESSAGE);
    }
}
