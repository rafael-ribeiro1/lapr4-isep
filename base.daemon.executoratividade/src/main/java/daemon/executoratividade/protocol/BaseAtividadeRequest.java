package daemon.executoratividade.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public abstract class BaseAtividadeRequest {
    protected final int VERSION;
    protected final String script;
    protected final Logger LOGGER;


    protected BaseAtividadeRequest(String script, final Class<?> clazz) {
        this.VERSION = 0;
        this.script = script;
        this.LOGGER= LogManager.getLogger(clazz);
    }

    protected BaseAtividadeRequest() {
        this.VERSION = 0;
        this.script=null;
        this.LOGGER = LogManager.getLogger(BaseAtividadeRequest.class);
    }

    public abstract SDP2021Packet execute();

    public String buildScriptErrorRequest(final String errorDescription){
        final BaseErrorRequest r = new BaseErrorRequest(script, errorDescription) {

            @Override
            protected String messageType() {
                return ERROR_MESSAGE_TYPE_SCRIPT;
            }
        };
        return r.buildErrorMessage();
    }

    public String buildScriptSuccessfulRequest(){
        final SuccessfulRequest r = new SuccessfulRequest(script);
        return r.buildSuccessfulRequest();
    }

    public SDP2021Packet buildTestRequest(){
        final BaseInformacaoRequest r = new TesteRequest(script);
        return r.execute();
    }

    public SDP2021Packet buildEntendidoRequest(){
        final BaseInformacaoRequest r = new EntendidoRequest(script);
        return r.execute();
    }

}
