package eapli.base.criticidademanagement.application;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.common.Cor;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.domain.EtiquetaCriticidade;
import eapli.base.criticidademanagement.domain.ObjetivosCriticidade;
import eapli.base.criticidademanagement.domain.ValorCriticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.awt.*;

@UseCaseController
public class DefinirCriticidadeController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();
    public Criticidade registarCriticidade(final String etiqueta,final int valorCriticidade,
                                           final Color corEscolhida, final int temMaxApro,final int temAvgAprova,
                                           final int tempMaxReso,final int tempAvgReso){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_SH,BaseRoles.POWER_USER);
        final Criticidade criticidade = new Criticidade(new Cor(corEscolhida.getRed(),corEscolhida.getGreen(),corEscolhida.getBlue()),
                new EtiquetaCriticidade(etiqueta),new ObjetivosCriticidade(temMaxApro,tempMaxReso,temAvgAprova,tempAvgReso),new ValorCriticidade(valorCriticidade));
        CriticidadeRepository repository = factory.criticidades();
        return repository.save(criticidade);
    }

}
