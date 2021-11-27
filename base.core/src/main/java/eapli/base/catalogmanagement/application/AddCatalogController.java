package eapli.base.catalogmanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

@UseCaseController
public class AddCatalogController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    public Iterable<Colaborador> allColaboradores() {
        return factory.colaboradores().findAll();
    }

    public Iterable<Equipa> allEquipas() {
        return factory.equipas().findAll();
    }

    public Catalog addCatalog(final String title, final String briefDesc, final String completeDesc,
                              final byte[] icon, final Colaborador resp, List<Equipa> accessAllowed) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_SH, BaseRoles.POWER_USER);

        final Catalog catalog = new Catalog(title, briefDesc, completeDesc, icon, resp, accessAllowed);
        CatalogRepository repo = factory.catalogs();
        return repo.save(catalog);
    }

}
