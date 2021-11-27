package daemon.motorfluxo.protocol;

import daemon.motorfluxo.application.InitiateFluxController;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public class ManageFluxRequestV0 extends BaseFluxRequest {

    public ManageFluxRequestV0(final InitiateFluxController controller, final String ticketId) {
        super (ticketId);
        this.VERSION=0;
    }

    @Override
    public SDP2021Packet execute() {
        SDP2021Packet response=new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, TICKET_OPENED, true);
        FluxoAtividadesPedido flux=getFluxByTickedId ( ticketId );

        boolean approved=true;
        if(flux.hasAtividadeAprovacao ()){
            updateActivityFluxState (EstadoPedido.EM_APROVACAO, APPROVAL_TASK_EXECUTED);
            approved=true;//equals to the result of the approval
        }
        if(!approved){
            return endConn (EstadoPedido.REJEITADO, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, TICKET_REJECTED, TICKET_REJECTED);
        }
        updateActivityFluxState (EstadoPedido.APROVADO, TICKET_APPROVED);
        if(flux.isRealizacaoAutomatica ()){
            response=sendScriptToExecutor (flux );
            updateStateIfScriptExecutedSuccessfully (response);
            return response;
        }
        //executar tarefa manualmente
        updateActivityFluxState (EstadoPedido.EM_RESOLUCAO,MANUAL_TASK_EXECUTED);
        //fechar ticket
        updateActivityFluxState (EstadoPedido.CONCLUIDO,TICKET_CLOSED );
        return response;
    }
}
