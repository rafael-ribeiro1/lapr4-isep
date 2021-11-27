package daemon.motorfluxo.protocol;

import eapli.base.Application;
import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_ENTENDIDO;
import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.TEST_MESSAGE;

public class TestRequest extends InformationRequest{

    public TestRequest(String inputRequest) {
        super(inputRequest);
    }

    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(Application.settings().getSdpProtocolVersion(),CODIGO_ENTENDIDO,TEST_MESSAGE);
    }
}
