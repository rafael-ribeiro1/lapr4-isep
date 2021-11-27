package eapli.base.persistence.impl.jpa;
import eapli.base.activity.domain.*;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.EstadoPedido;
import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.pedidoservico.dto.PedidoDTO;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;
import java.util.*;

public class JpaPedidoRepository
        extends BaseJpaRepositoryBase<Pedido, String, String>
        implements PedidoRepository {

    public JpaPedidoRepository() {
        super("id");
    }


    @Override
    public Iterable<RealizacaoManual> findAllAtividadesPendentesNaoAtribuidas(Iterable<Equipa> e) {
       TypedQuery<RealizacaoManual>  q = entityManager().createQuery("SELECT r FROM RealizacaoManual r\n" +
                "INNER JOIN FluxoAtividadesPedido f \n" +
                "ON f.atividadeRealizacao = r AND f.tipoAtividadeRealizacao = :tipo\n" +
                "where :equipa member of r.equipas AND r.dataLimite.data > :now AND r.responsavel = NULL  AND r.estado = :estado", RealizacaoManual.class);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        return q.getResultList();
    }


    @Override
    public Pedido findPedidoWithAtividade(AtividadeRealizacao atividadeRealizacao){
        System.out.println(""+Calendar.getInstance());
        TypedQuery<Pedido> q = entityManager().createQuery("SELECT p FROM Pedido p\n" +
                "INNER JOIN FluxoAtividadesPedido f\n" +
                "ON f = p.fluxoAtividades\n" +
                "WHERE p.fluxoAtividades.atividadeRealizacao = :atividade AND p.fluxoAtividades.tipoAtividadeRealizacao = :tipo \n" +
                "AND p.fluxoAtividades.atividadeRealizacao.dataLimite.data > :now AND p.fluxoAtividades.atividadeRealizacao.estado = :estado", Pedido.class);
        q.setParameter("atividade", atividadeRealizacao);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado",EstadoAtividade.PRONTO_EXECUTAR);
        return q.getSingleResult();
    }

    public Iterable<RealizacaoManual> filterByCriticidade(Criticidade c, List<Equipa> e){
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery("SELECT r FROM RealizacaoManual r " +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r "+
                "INNER JOIN Pedido p ON p.fluxoAtividades = f " +
                "where :equipa member of r.equipas AND p.service.criticality = :c AND r.responsavel = NULL AND f.tipoAtividadeRealizacao = :tipo \n" +
                " AND r.dataLimite.data > :now AND r.estado=:estado", RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("c", c);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    public Iterable<RealizacaoManual> filterByIntervaloDataLimite(DataLimite d, DataLimite d1,  List<Equipa> e){
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery("SELECT r FROM RealizacaoManual r\n" +
                "where :equipa member of  r.equipas AND (r.dataLimite.data BETWEEN :d AND :d1) AND r.responsavel = NULL\n" +
                " AND r.dataLimite.data > :now AND r.estado = :estado", RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("d", d.data());
        q.setParameter("d1", d1.data());
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }


    @Override
    public Iterable<RealizacaoManual> filterByUrgencia(Urgencia urgencia, List<Equipa> e) {
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery("SELECT r FROM RealizacaoManual r " +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r "+
                "INNER JOIN Pedido p ON p.fluxoAtividades = f " +
                "where :equipa member of r.equipas AND p.urgencia = :urgencia AND r.responsavel = NULL AND f.tipoAtividadeRealizacao = :tipo \n" +
                " AND r.dataLimite.data > :now AND r.estado = :estado", RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("urgencia", urgencia);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    public Iterable<RealizacaoManual> sortByCriticidade(boolean ascendente,  List<Equipa> e){
        String query;
        query = "SELECT r FROM RealizacaoManual r \n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r \n"+
                "INNER JOIN Pedido p ON p.fluxoAtividades = f \n" +
                "where :equipa member of r.equipas AND r.responsavel = NULL AND f.tipoAtividadeRealizacao = :tipo \n" +
                "AND r.dataLimite.data > :now AND r.estado = :estado order by p.service.criticality";
        if (!ascendente)
            query = query.concat(" desc");
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery(query, RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    public Iterable<RealizacaoManual> sortByDataLimite(boolean ascendente, List<Equipa> e){
        String query;
        query = "SELECT r FROM RealizacaoManual r\n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r \n"+
                "where :equipa member of r.equipas AND r.responsavel = NULL AND  f.tipoAtividadeRealizacao = :tipo \n " +
                "AND r.dataLimite.data > :now AND r.estado = :estado order by r.dataLimite";
        if (!ascendente)
            query = query.concat(" desc");
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery(query, RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    public Iterable<RealizacaoManual> sortByPrioridade(boolean ascendente, List<Equipa> e){
        String query;
        query = "SELECT r FROM RealizacaoManual r\n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r \n" +
                "INNER JOIN Pedido p ON p.fluxoAtividades = f \n";
        if (!ascendente)
            query = query.concat("where :equipa member of r.equipas AND r.responsavel = NULL AND r.dataLimite.data > :now AND f.tipoAtividadeRealizacao = :tipo AND r.estado = :estado order by p.urgencia desc, r.dataLimite desc, p.service.criticality desc");
        else{
            query = query.concat("where :equipa member of r.equipas AND r.responsavel = NULL AND r.dataLimite.data > :now AND f.tipoAtividadeRealizacao = :tipo AND r.estado = :estado order by p.urgencia, r.dataLimite, p.service.criticality");
        }
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery(query, RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    @Override
    public Iterable<RealizacaoManual> sortByUrgencia(boolean ascendente, List<Equipa> e) {
        String query;
        query = "SELECT r FROM RealizacaoManual r \n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao = r \n"+
                "INNER JOIN Pedido p ON p.fluxoAtividades = f \n" +
                "where :equipa member of r.equipas AND r.responsavel = NULL AND f.tipoAtividadeRealizacao = :tipo \n " +
                "AND r.dataLimite.data > :now AND r.estado = :estado order by p.urgencia";
        if (!ascendente)
            query = query.concat(" desc");
        TypedQuery<RealizacaoManual>  q = entityManager().createQuery(query, RealizacaoManual.class);
        q.setParameter("equipa", e);
        q.setParameter("tipo", TipoAtividadeRealizacao.MANUAL);
        q.setParameter("now", Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("estado", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    @Override
    public List<Pedido> approvalOfUser(Colaborador colab) {
        TypedQuery<Pedido> q = entityManager().createQuery("SELECT p FROM Pedido p " +
                "JOIN p.fluxoAtividades.atividadeAprovacao a " +
                "WHERE p.estado = :estado " +
                "AND a.responsavel = :colab " +
                "AND a in (select at from Atividade at where at.estado = :pending)", Pedido.class);
        q.setParameter("estado", EstadoPedido.EM_APROVACAO);
        q.setParameter("colab", colab);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }

    @Override
    public List<Pedido> realizationOfUser(Colaborador colab) {
        TypedQuery<Pedido> q = entityManager().createQuery("SELECT p FROM Pedido p " +
                "JOIN p.fluxoAtividades.atividadeRealizacao a " +
                "WHERE p.estado = :estado " +
                "AND a in (select rm from RealizacaoManual rm where rm.responsavel = :colab) " +
                "AND a in (select at from Atividade at where at.estado = :pending)", Pedido.class);
        q.setParameter("estado", EstadoPedido.EM_RESOLUCAO);
        q.setParameter("colab", colab);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);
        return q.getResultList();
    }



    @Override
    public int numberPendingActivities(Colaborador colaborador) {
        String query  = ("SELECT a FROM Atividade a \n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND (a in (select r from RealizacaoManual r  where r.responsavel = :c) \n" +
                "OR a in (select ap from AtividadeAprovacao ap where ap.responsavel = :c)) AND (a.dataLimite.data > :start ) AND a.estado = :pending order by a.dataLimite.data");


        TypedQuery<Atividade>  q = entityManager().createQuery(query, Atividade.class);

        q.setParameter("c", colaborador);
        q.setParameter("start",Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);

        return q.getResultList().size();
    }

    @Override
    public int numberLatePendingActivities(Colaborador colaborador) {

        String query  = ("SELECT a FROM Atividade a \n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND (a in (select r from RealizacaoManual r  where r.responsavel = :c) \n" +
                "OR a in (select ap from AtividadeAprovacao ap where ap.responsavel = :c)) AND (a.dataLimite.data < :start ) AND a.estado = :pending order by a.dataLimite.data");


        TypedQuery<Atividade>  q = entityManager().createQuery(query, Atividade.class);

        q.setParameter("c", colaborador);
        q.setParameter("start",Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);


        return q.getResultList().size();
    }

    @Override
    public int oneHourAwayPendingActitivities(Colaborador colaborador) {

        String query  = ("SELECT a FROM Atividade a \n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND (a in (select r from RealizacaoManual r  where r.responsavel = :c) \n" +
                "OR a in (select ap from AtividadeAprovacao ap where ap.responsavel = :c)) AND (a.dataLimite.data BETWEEN :start AND :end) AND a.estado = :pending order by a.dataLimite.data");


        TypedQuery<Atividade>  q = entityManager().createQuery(query, Atividade.class);

        q.setParameter("c", colaborador);
        q.setParameter("start",Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);

        Calendar in1Hour = Calendar.getInstance();
        in1Hour.add(Calendar.MINUTE,60);
        q.setParameter("end", in1Hour,TemporalType.TIMESTAMP);


        return q.getResultList().size();
    }

    @Override
    public List<Atividade> urgencyPendingActivities(Colaborador colaborador, Urgencia urgencia,boolean ascendingOrder) {


        String query = "SELECT a FROM Atividade a\n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND(a in (select r from RealizacaoManual r \n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao=r \n" +
                "INNER JOIN Pedido p ON p.fluxoAtividades=f \n"+
                "where r.responsavel = :c AND r.estado = :pending AND p.urgencia = :urgencia) \n" +
                " OR a in (select ap from AtividadeAprovacao ap \n" +
                "INNER JOIN FluxoAtividadesPedido  f ON f.atividadeAprovacao=ap \n" +
                "INNER JOIN Pedido p ON p.fluxoAtividades=f \n" +
                "where ap.responsavel = :c AND a.estado = :pending AND p.urgencia = :urgencia)) order by a.dataLimite.data";

        if (!ascendingOrder)
            query = query.concat(" DESC ");

        TypedQuery<Atividade>  q = entityManager().createQuery(query,Atividade.class);

        q.setParameter("c", colaborador);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);
        q.setParameter("urgencia",urgencia);


        return q.getResultList();
    }


    @Override
    public List<Atividade> PendingActivitiesByCriticality(Colaborador colaborador,Criticidade c,boolean order) {

        /*
            TypedQuery<Atividade> q = entityManager().createQuery("SELECT a FROM  Atividade a \n"+
                    "INNER JOIN RealizacaoManual rm on a=rm \n"+
                    "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao=rm \n"+
                    "INNER JOIN Pedido p ON p.fluxoAtividades=f \n"+
                    "INNER JOIN Service s on p.service=s " +
                    "INNER JOIN Criticidade ct on s.criticality = ct " +
                    " Where rm.responsavel = :colaborador AND rm.estado = :pending AND ct = :criticidade",Atividade.class);
    */

        String query = "SELECT a FROM Atividade a\n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND a in (select r from RealizacaoManual r \n" +
                "INNER JOIN FluxoAtividadesPedido f ON f.atividadeRealizacao=r \n" +
                "INNER JOIN Pedido p ON p.fluxoAtividades=f \n"+
                "INNER JOIN Service s on p.service=s \n"+
                "INNER JOIN Criticidade ct on s.criticality = ct \n"+
                "where r.responsavel = :c AND r.estado = :pending AND ct = :criticidade) OR " +
                "a in (select ap from AtividadeAprovacao ap \n" +
                "INNER JOIN FluxoAtividadesPedido  f ON f.atividadeAprovacao=ap \n" +
                "INNER JOIN Pedido p ON p.fluxoAtividades=f \n" +
                "INNER JOIN Service s on p.service = s \n"+
                "INNER JOIN Criticidade ct on s.criticality = ct \n " +
                "where ap.responsavel = :c AND a.estado = :pending AND ct = :criticidade) order by a.dataLimite.data ";

        if (!order)
            query = query.concat(" DESC ");

            TypedQuery<Atividade>  q = entityManager().createQuery(  query, Atividade.class);

            q.setParameter("c", colaborador);
            q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);
            q.setParameter("criticidade",c);

        return q.getResultList();
    }

    @Override
    public List<Atividade> pendingActivitiesByData(Colaborador colaborador, Calendar dataLimite,boolean asceningOrder) {

        String query  = ("SELECT a FROM Atividade a \n" +
                "where a not in (select at from RealizacaoAutomatica  at) \n" +
                "AND (a in (select r from RealizacaoManual r  where r.responsavel = :c) \n" +
                "OR a in (select ap from AtividadeAprovacao ap where ap.responsavel = :c)) AND (a.dataLimite.data BETWEEN :start AND :end) AND a.estado = :pending order by a.dataLimite.data");

        if (!asceningOrder)
            query = query.concat(" DESC ");

        TypedQuery<Atividade>  q = entityManager().createQuery(query, Atividade.class);

        q.setParameter("c", colaborador);
        q.setParameter("start",Calendar.getInstance(),TemporalType.TIMESTAMP);
        q.setParameter("pending", EstadoAtividade.PRONTO_EXECUTAR);
        q.setParameter("end", dataLimite,TemporalType.TIMESTAMP);

        return q.getResultList();
    }

    @Override
    public FluxoAtividadesPedido getActivityFluxByTicketId(final String id) {
        String query;
        query = "SELECT p \n"+
                "FROM Pedido p\n"+
                "where p.id =:id";
        TypedQuery<Pedido> q=entityManager ().createQuery ( query,Pedido.class );
        q.setParameter ( "id",id );
        Pedido p=q.getSingleResult ();
        return p.fluxoAtividades ();
    }

    @Override
    public void updateTicketState(final String id,final EstadoPedido state) {
        EntityTransaction tx=entityManager ().getTransaction ();
        tx.begin ();
        Query q= entityManager ().createQuery ("UPDATE Pedido p\n" +
                "SET p.estado = :state\n" +
                "where p.id= :id");

        q.setParameter ( "state",state );
        q.setParameter ( "id",id );
        q.executeUpdate ();
        tx.commit ();
    }


    @Override
    public void updateActivityState(Atividade atividade, EstadoAtividade estadoAtividade) {
        EntityTransaction tx=entityManager ().getTransaction ();
        tx.begin();
        Query q= entityManager ().createQuery ("UPDATE Atividade a\n" +
                "SET a.estado = :state\n" +
                "where a= :atividade");

        q.setParameter ( "state",estadoAtividade);
        q.setParameter ( "atividade",atividade );
        q.executeUpdate ();
        tx.commit ();
    }

    @Override
    public boolean hasLowestWorkLoad(Colaborador colab,List<Equipa> listaEquipas) {
        String query;
        query = "SELECT c \n"+
                "FROM Colaborador c\n"+
                "JOIN  RealizacaoManual r on r.responsavel = c \n"+
                "inner join FluxoAtividadesPedido fluxo on fluxo.atividadeRealizacao = r\n"+
                "inner join Pedido  pedido on pedido.fluxoAtividades=fluxo\n"+
                "inner join Service service on pedido.service=service\n"+
                "inner join Criticidade  criticidade on service.criticality=criticidade\n"+
                "where r.estado =:prontoExecutar and \n" +
                "c in (Select colaboradores from Equipa  e inner join e.colaboradores colaboradores where e in :listaEquipas)\n" +
                "ORDER BY count(r), sum(criticidade.objetivosCriticidade.tempoMedioResolucao)";


        TypedQuery<Colaborador>  q = entityManager().createQuery(query, Colaborador.class);
        q.setParameter ( "prontoExecutar",EstadoAtividade.PRONTO_EXECUTAR);
        q.setParameter("listaEquipas",listaEquipas);

        Colaborador leastWork;

        try{
            leastWork = q.getSingleResult();
        }catch (NoResultException e){
            System.out.println("No result exception");
            return false;
        }

        return leastWork.equals(colab);
    }


    @Override
    public void getTicketsOrderedByMostRecent(final SystemUser user,
                                              Collection<PedidoDTO> pendingTicketsDTO,
                                              Collection<PedidoDTO> concludedTicketsDTO){
        String query;
        query = "SELECT p \n"+
                "FROM Pedido p\n" +
                "INNER JOIN Colaborador c ON c.emailInstitucional.email=:userEmail\n" +
                "INNER JOIN Service s ON s =p.service\n" +
                "ORDER BY p.dataSolicitacao";

        TypedQuery<Pedido>  q = entityManager().createQuery(query, Pedido.class);
        q.setParameter ( "userEmail",user.email ().toString () );
        List<Pedido> tickets=q.getResultList ();
        for (Pedido ticket : tickets) {
            if(ticket.isPedidoConcluido ()){
                concludedTicketsDTO.add ( ticket.toDTO () );
            }else{
                pendingTicketsDTO.add ( ticket.toDTO () );
            }
        }
    }
}
