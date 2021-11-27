package eapli.base.activity.application;

import eapli.base.activity.domain.Atividade;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.criticidademanagement.application.ListCriticidadesService;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.*;

public class VerAtividadesPendentesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    private final PedidoRepository pedidoRepository = factory.pedidos();
    private final Colaborador loggedColaborator = factory.colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf((authz.session().orElse(null)).authenticatedUser().email().toString()));
    public List<Atividade> searchByCriticidadePendingActivities(Criticidade selectedCriticidade,boolean order) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        if(loggedColaborator == null) throw new IllegalArgumentException("Invalid User logged");
        return pedidoRepository.PendingActivitiesByCriticality(loggedColaborator,selectedCriticidade,order);
    }

    public Iterable<Criticidade> listAllCriticidades(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        if(loggedColaborator == null) throw new IllegalArgumentException("Invalid User logged");
        ListCriticidadesService service = new ListCriticidadesService();
        return service.listCriticidades();
    }

    public List<Atividade> searchByLimitDateActivities(Calendar limitDate,boolean ascendingOrder) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        if(loggedColaborator == null) throw new IllegalArgumentException("Invalid User logged");
        return pedidoRepository.pendingActivitiesByData(loggedColaborator,limitDate,ascendingOrder);
    }

    public List<Atividade> searchByUrgenciasPendingActivities(Urgencia urgencia, boolean ascending) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        if(loggedColaborator == null) throw new IllegalArgumentException("Invalid User logged");
        return pedidoRepository.urgencyPendingActivities(loggedColaborator,urgencia,ascending);
    }

    public String seeDetailedInfo(Atividade a) {
        return a.detailedInformation();
    }
}
