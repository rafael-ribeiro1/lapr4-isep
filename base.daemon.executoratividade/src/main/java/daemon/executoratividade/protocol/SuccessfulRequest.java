package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public class SuccessfulRequest  extends BaseAtividadeRequest {

    public SuccessfulRequest(String script) {
        super(script, SuccessfulRequest.class);
    }

    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(VERSION, CODIGO_RES_SCRIPT, buildSuccessfulRequest());
    }

    protected String buildSuccessfulRequest(){
        return String.format("%s - %s\n", messageType(), SUCCESSFUL_MESSAGE_SCRIPT);
    }

    protected String messageType(){
        return SUCCESSFUL_MESSAGE_TYPE_SCRIPT;
    }
}
