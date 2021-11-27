package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_ERRO;

public abstract class BaseErrorRequest extends BaseAtividadeRequest {

    private final String errorDescription;

    protected BaseErrorRequest(String script, String errorDescription) {
        super(script, BaseErrorRequest.class);
        this.errorDescription = errorDescription;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    @Override
    public SDP2021Packet execute() {
        return new SDP2021Packet(VERSION, CODIGO_ERRO, buildErrorMessage());
    }

    protected String buildErrorMessage(){
        return String.format("%s - %s\n", messageType(), errorDescription);
    }

    protected abstract String messageType();
}
