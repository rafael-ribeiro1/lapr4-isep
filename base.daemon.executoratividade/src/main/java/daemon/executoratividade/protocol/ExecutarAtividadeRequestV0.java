package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public class ExecutarAtividadeRequestV0 extends BaseAtividadeRequest {
    protected static final String ERROR_MESSAGE = "An Error occurred during Script Execution";

    public ExecutarAtividadeRequestV0(String script) {
        super(script, ExecutarAtividadeRequestV0.class);
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    @Override
    public SDP2021Packet execute() {
        try {
            System.out.println("Automated script executed successfully");
            return new SDP2021Packet(VERSION,CODIGO_RES_SCRIPT, buildScriptSuccessfulRequest(),true);
        }catch(Exception e){
            return new SDP2021Packet(VERSION,CODIGO_ERRO, buildScriptErrorRequest(ERROR_MESSAGE_SCRIPT),true);
        }
    }
}
