package eapli.base.persistence.impl.inmemory;

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
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.*;


public class InMemoryPedidoRepository
        extends InMemoryDomainRepository<Pedido, String>
        implements PedidoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<RealizacaoManual> findAllAtividadesPendentesNaoAtribuidas(Iterable<Equipa> e) {
        Iterable<Pedido> p;
        List<Equipa> l = new ArrayList<>();
        for (Equipa eq : e) l.add(eq);
        List<RealizacaoManual> result = new ArrayList<>();
        p = match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(l));
        for (Pedido pedido : p){
            result.add(((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()));
        }
        return result;
    }

    @Override
    public Pedido findPedidoWithAtividade(AtividadeRealizacao atividadeRealizacao) {
        Optional<Pedido> opt = matchOne(pedido -> pedido.fluxoAtividades().atividadeRealizacao().equals(atividadeRealizacao));
        return opt.orElse(null);
    }

    @Override
    public Iterable<RealizacaoManual> sortByPrioridade(boolean ascendente, List<Equipa> e) {
        List<RealizacaoManual> l = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        for (Pedido p : this.findAll()){
            if (((RealizacaoManual)p.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(e)){
                pedidos.add(p);
            }
        }
        pedidos.sort((o1, o2) -> {
            if(o1.service().catalog().criticidade().valor().valor() > o2.service().catalog().criticidade().valor().valor())
                return 1;
            else if (o1.service().catalog().criticidade().valor().valor() < o2.service().catalog().criticidade().valor().valor())
                return -1;
            else
                return o1.fluxoAtividades().atividadeRealizacao().dataLimite().
                        compareTo(o2.fluxoAtividades().atividadeRealizacao().dataLimite()) | o1.urgencia().compareTo(o2.urgencia());

        });
        for (Pedido p : pedidos){
            l.add((RealizacaoManual) p.fluxoAtividades().atividadeRealizacao());
        }
        return l;
    }

    @Override
    public Iterable<RealizacaoManual> sortByDataLimite(boolean ascendente, List<Equipa> e) {
        List<RealizacaoManual> l = new ArrayList<>();
        for (RealizacaoManual r : this.findAllAtividadesPendentesNaoAtribuidas(e))
            l.add(r);
        l.sort(Comparator.comparing(Atividade::dataLimite));
        if (!ascendente)
            Collections.reverse(l);
        return l;
    }

    @Override
    public Iterable<RealizacaoManual> sortByCriticidade(boolean ascendente, List<Equipa> e) {
        List<RealizacaoManual> l = new ArrayList<>();
        List<Pedido> aux = new ArrayList<>();
        for (Pedido p : match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(e)))
            aux.add(p);
        aux.sort(Comparator.comparing(o -> o.service().getCriticality().identity()));
        for (int i = 0; i < aux.size(); i++) {
           l.add((RealizacaoManual)aux.get(i).fluxoAtividades().atividadeRealizacao());
        }
        if (!ascendente)
            Collections.reverse(l);
        return l;
    }

    @Override
    public Iterable<RealizacaoManual> filterByIntervaloDataLimite(DataLimite d, DataLimite d1, List<Equipa> e) {
        List<RealizacaoManual> l = new ArrayList<>();
        for(RealizacaoManual r : this.findAllAtividadesPendentesNaoAtribuidas(e)){
           if (r.dataLimite().compareTo(d) > 0 && r.dataLimite().compareTo(d1) < 0)
               l.add(r);
        }
       return l;
    }

    @Override
    public Iterable<RealizacaoManual> filterByCriticidade(Criticidade c, List<Equipa> e) {
        Iterable<Pedido> p;
        List<RealizacaoManual> result = new ArrayList<>();
        p = match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(e));
        for (Pedido pedido : p){
            if (pedido.service().catalog().criticidade().valor().valor() == c.valor().valor())
                result.add(((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()));
        }
        return result;
    }

    @Override
    public int numberPendingActivities(Colaborador colaborador) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colaborador));
        int result = 0 ;
        for(Pedido p : pedidos){
            RealizacaoManual realizacaoManual = (RealizacaoManual) p.fluxoAtividades().atividadeRealizacao();
            if(realizacaoManual.equals(colaborador)){
                result++;
            }
        }
        return result;
    }

    @Override
    public int numberLatePendingActivities(Colaborador colaborador) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colaborador));
        int result = 0 ;
        for(Pedido p : pedidos){
            RealizacaoManual realizacaoManual = (RealizacaoManual) p.fluxoAtividades().atividadeRealizacao();
            if(realizacaoManual.equals(colaborador) && realizacaoManual.reivindicarAtividadePendente(colaborador) ){
                result++;
            }
        }
        return result;
    }

    @Override
    public int oneHourAwayPendingActitivities(Colaborador colaborador) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colaborador));
        int result = 0 ;
        for(Pedido p : pedidos){
            AtividadeAprovacao ap = p.fluxoAtividades().atividadeAprovacao();
            if(ap!=null){
                if(ap.dataLimite().data().after(new Date())) result++;
            }
            AtividadeRealizacao realizacao = p.fluxoAtividades().atividadeRealizacao();
            if(realizacao!=null){
                if(realizacao.dataLimite().data().after(new Date())) result++;
            }
        }
        return result;
    }

    @Override
    public List<Atividade> urgencyPendingActivities(Colaborador colaborador, Urgencia urgencia,boolean ascendingOrder) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colaborador));
        List<Atividade> list = new ArrayList<>();
        for(Pedido p : pedidos){
           if(p.urgencia()!=urgencia) continue;
           AtividadeAprovacao ap = p.fluxoAtividades().atividadeAprovacao();
           if(ap!=null){
               if(ap.responsavel().equals(colaborador)) list.add(ap);
           }
           AtividadeRealizacao realizacao = p.fluxoAtividades().atividadeRealizacao();
           if(realizacao!=null){
               if(realizacao instanceof RealizacaoManual && ((RealizacaoManual) realizacao).equipas().contains(colaborador)){
                   list.add(realizacao);
               }
           }
        }
        return list;
    }

    @Override
    public List<Atividade> PendingActivitiesByCriticality(Colaborador colaborador,Criticidade c,boolean order) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colaborador));
        List<Atividade> lista = new ArrayList<>();
        for(Pedido p : pedidos){
            AtividadeAprovacao a = p.fluxoAtividades().atividadeAprovacao();
            AtividadeRealizacao b = p.fluxoAtividades().atividadeRealizacao();
            if(a!=null && a.responsavel()!=null){
                if(a.responsavel().equals(colaborador)){
                    lista.add(a);
                }
            }
            if(b!=null && a.responsavel()!=null){
                if(b.equals(colaborador)){
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    @Override
    public List<Atividade> pendingActivitiesByData(Colaborador colaborador, Calendar dataLimite,boolean asceningOrder) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> (pedido.fluxoAtividades().atividadeAprovacao().responsavel().equals(colaborador)))  ;
        List<Atividade> lista = new ArrayList<>();
        for(Pedido p : pedidos){
            AtividadeAprovacao a = p.fluxoAtividades().atividadeAprovacao();
            AtividadeRealizacao b = p.fluxoAtividades().atividadeRealizacao();
            if(a!=null ){
                if(a.dataLimite().data().compareTo(dataLimite)<0){
                    lista.add(a);
                }
            }
            if(b!=null){
                if(b.dataLimite().data().compareTo(dataLimite)<0){
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    @Override
    public FluxoAtividadesPedido getActivityFluxByTicketId(String id) {
        Iterable<Pedido> pedidos = this.findAll();
        for(Pedido p : pedidos){
            if(p.fluxoAtividades().identifier().equals(id)) return p.fluxoAtividades();
        }
        return null;
    }

    @Override
    public void updateTicketState(final String id,final EstadoPedido state) {
        Pedido pedido = this.findById(id).get();
        pedido.atualizarEstadoPedido(state);
    }

    @Override
    public void getTicketsOrderedByMostRecent(final SystemUser user,Collection<PedidoDTO> pendingTicketsDTO,
                                                             Collection<PedidoDTO> concludedTicketsDTO){
        Iterable<Pedido> tickets=findAll ();
        for (Pedido ticket : tickets) {
            if(ticket.colaboradorOfRequest ()
                    .emailInstitucional ()
                    .email ()
                    .equals (user.email ().toString ())
            ){
                if(ticket.isPedidoConcluido ())
                    concludedTicketsDTO.add ( ticket.toDTO () );
                else
                    pendingTicketsDTO.add ( ticket.toDTO () );
            }
        }
    }

    @Override
    public boolean hasLowestWorkLoad(Colaborador colab,List<Equipa> listaEquipas) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().contains(colab));
        List<Atividade> list = new ArrayList<>();
        for(Pedido p : pedidos){
            AtividadeRealizacao realizacao = p.fluxoAtividades().atividadeRealizacao();
            if(realizacao!=null){
                if(realizacao instanceof RealizacaoManual && ((RealizacaoManual) realizacao).equipas().contains(colab)){
                    list.add(realizacao);
                }
            }
        }
        return true;
    }

    @Override
    public void updateActivityState(Atividade atividade, EstadoAtividade estadoAtividade) {
        Iterable<Pedido>atividades;
        atividades=match(pedido-> pedido.fluxoAtividades().atividadeRealizacao().equals(atividade));
        for(Pedido a : atividades){
            if(!a.fluxoAtividades().atividadeRealizacao().equals(atividade)) continue;
            a.fluxoAtividades().atividadeRealizacao().updateEstadoAtividade(estadoAtividade);
        }
    }

    @Override
    public Iterable<RealizacaoManual> filterByUrgencia(Urgencia urgencia, List<Equipa> e) {
        Iterable<Pedido> p;
        List<RealizacaoManual> result = new ArrayList<>();
        p = match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(e));
        for (Pedido pedido : p){
            if (pedido.urgencia() == urgencia)
                result.add(((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()));
        }
        return result;

    }

    @Override
    public Iterable<RealizacaoManual> sortByUrgencia(boolean ascendente, List<Equipa> e) {
        List<RealizacaoManual> l = new ArrayList<>();
        List<Pedido> aux = new ArrayList<>();
        for (Pedido p : match(pedido -> ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).equipas().containsAll(e)))
            aux.add(p);
        aux.sort(Comparator.comparing(Pedido::urgencia));
        for (int i = 0; i < aux.size(); i++) {
            l.add((RealizacaoManual)aux.get(i).fluxoAtividades().atividadeRealizacao());
        }
        if (!ascendente)
            Collections.reverse(l);
        return l;
    }

    @Override
    public List<Pedido> approvalOfUser(Colaborador colab) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> pedido.fluxoAtividades().atividadeAprovacao().responsavel().equals(colab));
        return (List<Pedido>) pedidos;
    }

    @Override
    public List<Pedido> realizationOfUser(Colaborador colab) {
        Iterable<Pedido>pedidos;
        pedidos=match(pedido -> pedido.fluxoAtividades().atividadeRealizacao().equals(colab));
        return (List<Pedido>) pedidos;
    }

}
