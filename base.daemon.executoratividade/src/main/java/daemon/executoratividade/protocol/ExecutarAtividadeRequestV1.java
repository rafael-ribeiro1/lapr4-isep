package daemon.executoratividade.protocol;


import eapli.base.antlr.linguagemScripts.AutomatedScriptAPI;
import eapli.base.antlr.linguagemScripts.AutomatedScriptListenerAPI;
import eapli.base.protocolo.domain.SDP2021Packet;

import java.io.IOException;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;


public class ExecutarAtividadeRequestV1 extends BaseAtividadeRequest {
    protected final int VERSION=1;
    public ExecutarAtividadeRequestV1(String script) {
        super(script, ExecutarAtividadeRequestV1.class);
    }

    @Override
    public SDP2021Packet execute() {
            AutomatedScriptAPI api = new AutomatedScriptListenerAPI(script);
        try {
            if (api.executeScript()) {
                System.out.println("Automated script executed successfully");
                return new SDP2021Packet(VERSION, CODIGO_RES_SCRIPT,buildScriptSuccessfulRequest(), true);
            }else{
                return new SDP2021Packet(VERSION, CODIGO_ERRO, buildScriptErrorRequest(ERROR_MESSAGE_SCRIPT), true);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return new SDP2021Packet(VERSION, CODIGO_ERRO, buildScriptErrorRequest(ERROR_MESSAGE_SCRIPT), true);
    }

}
