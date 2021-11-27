package eapli.base.pedidoservico.repository;

import eapli.base.activity.domain.*;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.pedidoservico.dto.PedidoDTO;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public interface PedidoRepository extends DomainRepository<String, Pedido> {


    Iterable<RealizacaoManual> findAllAtividadesPendentesNaoAtribuidas(Iterable<Equipa> e);

    Pedido findPedidoWithAtividade(AtividadeRealizacao atividadeRealizacao);

    Iterable<RealizacaoManual> sortByPrioridade(boolean ascendente, List<Equipa> e);

    Iterable<RealizacaoManual> sortByDataLimite(boolean ascendente, List<Equipa> e);

    Iterable<RealizacaoManual> sortByCriticidade(boolean ascendente,  List<Equipa> e);

    Iterable<RealizacaoManual> filterByIntervaloDataLimite(DataLimite d, DataLimite d1, List<Equipa> e);

    Iterable<RealizacaoManual> filterByCriticidade(Criticidade c, List<Equipa> e);

    int numberPendingActivities (Colaborador colaborador);

    int numberLatePendingActivities(Colaborador colaborador);

    int oneHourAwayPendingActitivities(Colaborador colaborador);

    List<Atividade> urgencyPendingActivities(Colaborador colaborador, Urgencia urgencia,boolean ascendingOrder);


    List<Atividade> PendingActivitiesByCriticality(Colaborador colaborador,Criticidade c,boolean order);

    List<Atividade> pendingActivitiesByData(Colaborador colaborador, Calendar dataLimite,boolean asceningOrder);
    @Transactional
    FluxoAtividadesPedido getActivityFluxByTicketId(final String id);

    void updateTicketState(final String id,final EstadoPedido state);

    void getTicketsOrderedByMostRecent(final SystemUser user,Collection<PedidoDTO> pendingTicketsDTO,
                                       Collection<PedidoDTO> concludedTicketsDTO);

    Iterable<RealizacaoManual> filterByUrgencia(Urgencia urgencia, List<Equipa> e);

    Iterable<RealizacaoManual> sortByUrgencia(boolean ascendente, List<Equipa> e);

    List<Pedido> approvalOfUser(Colaborador colab);

    List<Pedido> realizationOfUser(Colaborador colab);

    void updateActivityState(Atividade atividade, EstadoAtividade estadoAtividade);

    boolean hasLowestWorkLoad(Colaborador colab,List<Equipa> listaEquipas);
}
