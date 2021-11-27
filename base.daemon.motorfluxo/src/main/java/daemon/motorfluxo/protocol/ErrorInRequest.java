package daemon.motorfluxo.protocol;

import eapli.base.Application;
import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_ERRO;
import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.ERROR_MESSAGE;

public class ErrorInRequest extends InformationRequest{

    public ErrorInRequest(String inputRequest) {
        super(inputRequest);
    }

    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(Application.settings().getSdpProtocolVersion(),CODIGO_ERRO,ERROR_MESSAGE);
    }
}
