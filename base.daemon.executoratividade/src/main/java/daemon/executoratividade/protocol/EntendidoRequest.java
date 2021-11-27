package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;

public class EntendidoRequest extends BaseInformacaoRequest {

    protected EntendidoRequest(String script) {
        super(script);
    }

    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(VERSION, SDP2021ProtocolTypeMessages.CODIGO_ENTENDIDO, buildInformationMessage());
    }
}
