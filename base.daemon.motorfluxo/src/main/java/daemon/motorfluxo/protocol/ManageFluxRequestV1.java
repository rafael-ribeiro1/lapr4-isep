package daemon.motorfluxo.protocol;

import daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.ExecutorsThreadsData;
import daemon.motorfluxo.application.InitiateFluxController;
import daemon.motorfluxo.application.InitiateFluxControllerImpl;
import daemon.motorfluxo.presentation.MotorFluxoServer;
import eapli.base.Application;
import eapli.base.activity.domain.AtividadeAprovacao;
import eapli.base.activity.domain.AtividadeRealizacao;
import eapli.base.activity.domain.EstadoAtividade;
import eapli.base.activity.domain.RealizacaoAutomatica;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.utils.ANSI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;

public class ManageFluxRequestV1 extends BaseFluxRequest{
    private static final Logger LOGGER = LogManager.getLogger(ManageFluxRequestV1.class);
    private InitiateFluxController controller=new InitiateFluxControllerImpl();

    protected final int VERSION=1;



    protected ManageFluxRequestV1(final String ticketId) {
        super (ticketId );
        //this.scriptQueue= ExecutorsThreadsData.scriptQueue ();
    }

    @Override
    public SDP2021Packet execute(){
        Pedido pedido = controller.getPedido(this.ticketId);
        if(pedido==null) return this.buildErrorMessage();
        return realizarAnalisePedido(pedido);
    }

    private SDP2021Packet realizarAnalisePedido(final Pedido pedido){
        switch (pedido.estadoAtualPedido()){
            case SUBMETIDO:
                return fazerAnaliseInicialPedido(pedido);
            case EM_APROVACAO:
                return verificarAprovacao(pedido);
            case EM_RESOLUCAO:
                return finalizarPedido(pedido);
            default:
                ANSI.print ( "ERROR",ANSI.RED );
                return buildErrorMessage();
        }
    }

    private SDP2021Packet fazerAnaliseInicialPedido(Pedido pedido) {
        FluxoAtividadesPedido flux= pedido.fluxoAtividades();
        if(flux.hasAtividadeAprovacao()) return iniciarAtividadeAprovacao(pedido);
        return iniciarExecucaoAtividadeRealizacao(pedido);
    }

    private SDP2021Packet iniciarAtividadeAprovacao(Pedido pedido) {
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        AtividadeAprovacao atividadeAprovacao = fluxo.atividadeAprovacao();
        this.updateActivityState(atividadeAprovacao,EstadoAtividade.PRONTO_EXECUTAR);
        this.updateActivityFluxState(EstadoPedido.EM_APROVACAO,EstadoPedido.EM_APROVACAO.toString());
        ANSI.log ( LOGGER::info,"Atividade de aprovacao inicializada", ANSI.CYAN);
        return new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, START_APPROVAL_TASK, true);
    }


    private SDP2021Packet verificarAprovacao(Pedido pedido) {
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        AtividadeAprovacao atividadeAprovacao = fluxo.atividadeAprovacao();
        this.updateActivityState(atividadeAprovacao,EstadoAtividade.CONCLUIDA);
        boolean result =  fluxo.resultadoAprovacao();
        if(!result)return acabarPedidoRejeitado(pedido);
        ANSI.log ( LOGGER::info,"Servico Aprovado", ANSI.YELLOW);
        this.updateActivityFluxState(EstadoPedido.APROVADO,EstadoPedido.APROVADO.toString());
        return iniciarExecucaoAtividadeRealizacao(pedido);
    }



    private SDP2021Packet acabarPedidoRejeitado(Pedido pedido) {
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        AtividadeRealizacao atividadeRealizacao = fluxo.atividadeRealizacao();
        this.updateActivityState(atividadeRealizacao,EstadoAtividade.CANCELADA);
        this.updateActivityFluxState(EstadoPedido.REJEITADO,EstadoPedido.REJEITADO.toString());
        ANSI.log ( LOGGER::info,"Servico Rejeitado", ANSI.RED);
        return new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, TICKET_REJECTED , true);
    }

    private SDP2021Packet iniciarExecucaoAtividadeRealizacao(Pedido pedido) {
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        this.updateActivityFluxState(EstadoPedido.EM_RESOLUCAO,EstadoPedido.EM_RESOLUCAO.toString());
        AtividadeRealizacao atividadeRealizacao = fluxo.atividadeRealizacao();
        this.updateActivityState(atividadeRealizacao,EstadoAtividade.PRONTO_EXECUTAR);
        return analisarExecucaoAtividadeRealizacao(pedido);
    }

    private SDP2021Packet analisarExecucaoAtividadeRealizacao(Pedido pedido) {
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        if(fluxo.isRealizacaoAutomatica()) return executarScriptAutomatico(pedido);
        return iniciarRealizacaoManual(pedido);
    }

    public SDP2021Packet executarScriptAutomatico(final Pedido pedido) {
        int executorResponse=sendScriptToExecutor ( pedido );
        logInfo ( "(Ticket id "+this.ticketId+")Script result: "+executorResponse );
        if(hasScriptExecutedSuccessfully(executorResponse)){
            return finalizarPedido(pedido);
        }
        logWarning ( "(Ticket id "+this.ticketId+")There was a problem executing the script" );
        return new SDP2021Packet(Application.settings().getSdpProtocolVersion(),CODIGO_ERRO,ERROR_MESSAGE);
    }

    private void logInfo(final String text){
        ANSI.log (LOGGER::info,text,ANSI.CYAN );
        ANSI.print ( text,ANSI.CYAN );
    }

    private void logWarning(final String text){
        ANSI.log ( LOGGER::warn,text,ANSI.UNDERLINE_CYAN );
        ANSI.print ( text,ANSI.UNDERLINE_CYAN );
    }

    protected int sendScriptToExecutor(final Pedido ticket){
        final String script=getScript (ticket);
        logInfo ( "(Ticket "+ticket.identity ()+")Sending script to executor server" );
        ExecutorsThreadsData.offerScriptToQueue (ticketId,script);
        Semaphore sem=ExecutorsThreadsData.getSemaphoreByTicketId ( ticketId );
        System.out.println ("(Ticket "+ticket.identity ()+")got thread semaphore");
        try {
            //wait for the ExecutorHandler thread to get the response from the executor
            sem.acquire ();
            System.out.println ("(Ticket "+ticket.identity ()+")acquired thread semaphore");
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        //apagar entradas do mapa e retornar o resultado da execução
        return ExecutorsThreadsData.removeData(ticketId);
    }

    private String getScript(final Pedido ticket){
        return ((RealizacaoAutomatica)ticket
                .fluxoAtividades ()
                .atividadeRealizacao ())
                .getScriptInStringFormat ();
    }

    private SDP2021Packet iniciarRealizacaoManual(final Pedido pedido) {
        boolean realizarAlgoritmoAtribuicao= Application.settings().getAutomaticallyAtributeCollaborators();
        if(realizarAlgoritmoAtribuicao) return algoritmoAtribuicao(pedido);
        ANSI.log ( LOGGER::info,"Atividade de Realizacao a ser Reindivicado", ANSI.GREEN);
        return new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, START_MANUAL_TASK_EXECUTED, true);
    }


    private SDP2021Packet algoritmoAtribuicao(Pedido pedido) {
        MotorFluxoServer.algorithmColaborador.addAtividade(pedido);
        ANSI.log ( LOGGER::info,"Colaborador Atribuido automaticamente a atividade", ANSI.GREEN);
        return new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, ACTIVITY_AUTOMATIC_ATTRIBUTION, true);
    }


    private SDP2021Packet finalizarPedido(Pedido pedido) {
        this.updateActivityFluxState(EstadoPedido.CONCLUIDO,EstadoPedido.CONCLUIDO.toString());
        FluxoAtividadesPedido fluxo = pedido.fluxoAtividades();
        AtividadeRealizacao atividadeRealizacao = fluxo.atividadeRealizacao();
        this.updateActivityState(atividadeRealizacao,EstadoAtividade.CONCLUIDA);
        ANSI.log ( LOGGER::info,"Servico finalizado com sucesso", ANSI.BOLD_WHITE);
        return new SDP2021Packet ( VERSION, CODIGO_RES_AVANCAR_FLUXO_PEDIDO, TICKET_CLOSED , true);
    }
}
