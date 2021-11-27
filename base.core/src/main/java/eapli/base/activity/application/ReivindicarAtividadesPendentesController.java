package eapli.base.activity.application;

import eapli.base.activity.domain.DataLimite;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.activity.domain.AtividadeRealizacao;
import eapli.base.activity.domain.RealizacaoManual;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Arrays;
import java.util.List;

@UseCaseController
public class ReivindicarAtividadesPendentesController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();
    private final SystemUser user = (authz.session().orElse(null)).authenticatedUser();

    public Pedido reivindicarAtividadePendente(Pedido pedido, ColaboradorDTO utilizador) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        Colaborador u = factory.colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf(utilizador.email));
        Preconditions.noneNull(pedido, u);
        if (pedido.atualizarPedidoAtividadePendente(u)){
            return this.factory.pedidos().save(pedido);
        }else {
            return null;
        }
    }

    public Iterable<RealizacaoManual> atividadesPendentesNaoAtribuidas(Iterable<Equipa> e){
        return factory.pedidos().findAllAtividadesPendentesNaoAtribuidas(e);
    }

    public Pedido pedidoWithAtividade(AtividadeRealizacao at){
        return factory.pedidos().findPedidoWithAtividade(at);
    }

    public List<Equipa> equipasUtilizadorAtual(){
        return  (List<Equipa>)findEquipasColaborador(colaborador(EmailInstitucional.valueOf(user.email().toString())).toDTO());
    }

    public ColaboradorDTO colaboradorAutenticado(){
        return (colaborador(EmailInstitucional.valueOf(user.email().toString()))).toDTO();
    }


    public Iterable<RealizacaoManual> sortByPrioridade(boolean ascendente, List<Equipa> e){
        return factory.pedidos().sortByPrioridade(ascendente, e);
    }

    public Iterable<RealizacaoManual> sortByDataLimite(boolean ascendente, List<Equipa> e){
        return factory.pedidos().sortByDataLimite(ascendente, e);
    }

    public Iterable<RealizacaoManual> sortByCriticidade(boolean ascendente,  List<Equipa> e){
        return factory.pedidos().sortByCriticidade(ascendente, e);
    }

    public Iterable<RealizacaoManual> filterByIntervaloDataLimite(DataLimite d, DataLimite d1, List<Equipa> e){
        return factory.pedidos().filterByIntervaloDataLimite(d, d1, e);
    }

    public Iterable<RealizacaoManual> filterByCriticidade(Criticidade c, List<Equipa> e){
        return factory.pedidos().filterByCriticidade(c, e);
    }

    private Colaborador colaborador(EmailInstitucional n){
        return factory.colaboradores().findColaboradorPorEmail(n);
    }

    private Iterable<Equipa> findEquipasColaborador(ColaboradorDTO c){
        Colaborador colab = factory.colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf(c.email));
        return factory.equipas().findEquipasColaborador(colab);
    }

    public List<Criticidade> findAllCriticidades(){
        return (List<Criticidade>)factory.criticidades().findAll();
    }

    public List<Urgencia> findAllUrgencias(){
        return Arrays.asList(Urgencia.values());
    }

    public Iterable<RealizacaoManual> sortByUrgencia(boolean ascendente, List<Equipa> e) {
        return factory.pedidos().sortByUrgencia(ascendente, e);
    }
    public Iterable<RealizacaoManual> filterByUrgencia(Urgencia urgencia, List<Equipa> e) {
        return factory.pedidos().filterByUrgencia(urgencia, e);
    }
}
