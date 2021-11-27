package daemon.motorfluxo.protocol;

import daemon.motorfluxo.application.InitiateFluxController;
import daemon.motorfluxo.application.InitiateFluxControllerImpl;
import eapli.base.Application;
import eapli.base.activity.domain.Atividade;
import eapli.base.activity.domain.EstadoAtividade;
import eapli.base.activity.domain.RealizacaoAutomatica;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;
import eapli.base.utils.ANSI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public abstract class BaseFluxRequest extends InformationRequest {

    protected final InitiateFluxController controller = new InitiateFluxControllerImpl();
    protected final String ticketId;
    protected  int VERSION;
    protected final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    protected static final String TICKET_OPENED="Ticket opened";

    protected static final String START_APPROVAL_TASK="Start Approval task";

    protected static final String APPROVAL_TASK_EXECUTED="Approval task executed";
    protected static final String TICKET_APPROVED ="Ticket Approved";
    protected static final String TICKET_REJECTED ="Ticket Rejected";
    protected static final String AUTOMATIC_TASK_EXECUTED="Automatic Task executed";

    protected static final String START_MANUAL_TASK_EXECUTED="Start Manual task";
    protected static final String ACTIVITY_AUTOMATIC_ATTRIBUTION="Manual task attributed";

    protected static final String MANUAL_TASK_EXECUTED="Manual Task executed";


    protected static final String TICKET_CLOSED="Ticket closed";

    protected BaseFluxRequest(final String ticketId) {
        super(ticketId);
        this.ticketId=ticketId;
        this.VERSION=1;
    }

    public abstract SDP2021Packet execute();



    protected SDP2021Packet generateScriptPacket(final FluxoAtividadesPedido flux){
        RealizacaoAutomatica task=(RealizacaoAutomatica)flux.atividadeRealizacao ();
        String script=task.getScriptInStringFormat ();
        return new SDP2021Packet (VERSION, SDP2021ProtocolTypeMessages.CODIGO_SCRIPT,script);
    }

    protected void updateActivityFluxState(final EstadoPedido state, final String logMessage){
        controller.updateActivityFluxState( ticketId, state);
        LOGGER.info(logMessage);
    }

    protected void updateActivityState(final Atividade atividade, final EstadoAtividade estadoAtividade){
        controller.updateActivityState(atividade,estadoAtividade);
    }

    protected void savePedido(final Pedido pedidoToSave){
        controller.savePedido(pedidoToSave);
    }

    protected SDP2021Packet endConn(final EstadoPedido state,
                                    final int code, final String logMessage, final String response){
        controller.updateActivityFluxState ( ticketId, state);
        LOGGER.info (logMessage);
        return new SDP2021Packet ( VERSION,code, response, true);
    }

    protected SDP2021Packet endConn(final int code, final String logMessage, final String response){
        LOGGER.info (logMessage);
        return new SDP2021Packet ( VERSION,code, response, true);
    }



    protected SDP2021Packet sendScriptToExecutor(final FluxoAtividadesPedido flux) {
        try {
            updateActivityFluxState (EstadoPedido.EM_RESOLUCAO,"Sending script to executor server" );
            SDP2021Packet request=generateScriptPacket ( flux );
            return SDP2021Client.simpleCommunication(request, Application.settings().getExecutorServerIp (),
                    Application.settings().getExecutorServerPort (), Application.settings().getCertClientName());
        }catch (IOException e) {
            String err_msg="Failed to establish communication with the Executor Server";
            err_msg=ANSI.format (err_msg,ANSI.RED);
            return endConn (CODIGO_ERRO, err_msg,err_msg);
        }
    }

    protected static boolean hasScriptExecutedSuccessfully(final SDP2021Packet response){
        return response.code ()==CODIGO_RES_SCRIPT;
    }

    protected static boolean hasScriptExecutedSuccessfully(final int code){
        return code==CODIGO_RES_SCRIPT;
    }

    protected boolean updateStateIfScriptExecutedSuccessfully(final SDP2021Packet response){
        if(!hasScriptExecutedSuccessfully ( response )){
            LOGGER.warn ( ANSI.format ( "There was a problem executing the script", ANSI.YELLOW));
            return false;
        }else{
            updateActivityFluxState(EstadoPedido.CONCLUIDO,AUTOMATIC_TASK_EXECUTED);
            return true;
        }
    }

    protected FluxoAtividadesPedido getFluxByTickedId(final String ticketId){
        return controller.getActivityFluxByTicketId ( ticketId );
    }
}
