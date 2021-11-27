package eapli.base.colaboratormanagement.application;

import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AddFuncaoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Funcao addFuncao(final String name) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH,BaseRoles.POWER_USER);

        final Funcao funcao = new Funcao(name);
        FuncaoRepository repo = PersistenceContext.repositories().funcoes();
        return repo.save(funcao);
    }

}
