package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;

public class BadRequest extends BaseErrorRequest{


    protected BadRequest(String script, String errorDescription) {
        super(script, errorDescription);
    }

    @Override
    protected String messageType() {
        return SDP2021ProtocolTypeMessages.ERROR_MESSAGE;
    }
}
