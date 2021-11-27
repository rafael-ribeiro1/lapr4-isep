package eapli.base.colaboratormanagement.application;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
public class ListColaboratorService {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

    public Iterable<Colaborador> allColaborators(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,BaseRoles.GESTOR_SH,BaseRoles.RESPONSAVEL_RH);
        return this.colaboradorRepository.findAll();
    }

}
