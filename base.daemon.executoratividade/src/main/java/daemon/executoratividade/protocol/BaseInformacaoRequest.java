package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;

public abstract class BaseInformacaoRequest extends BaseAtividadeRequest{

    protected BaseInformacaoRequest(String script) {
        super(script, BaseErrorRequest.class);
    }


    @Override
    public abstract SDP2021Packet execute();


    protected String buildInformationMessage(){
        return SDP2021ProtocolTypeMessages.EMPTY_MESSAGE;
    }
}
