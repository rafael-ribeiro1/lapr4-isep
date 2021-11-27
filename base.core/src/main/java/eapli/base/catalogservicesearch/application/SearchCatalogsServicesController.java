package eapli.base.catalogservicesearch.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.catalogservicesearch.domain.CatalogServiceSearchResult;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class SearchCatalogsServicesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    public CatalogServiceSearchResult searchCatalogsServices(String term) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);

        String email = (authz.session().orElse(null)).authenticatedUser().email().toString();
        Colaborador colab = factory.colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf(email));
        if (colab == null) throw new IllegalArgumentException("Invalid user logged in");

        CatalogRepository catalogRepository = factory.catalogs();
        List<Catalog> catalogs = catalogRepository.searchCatalogs(term, colab);

        ServiceRepository serviceRepository = factory.services();
        List<Service> services = serviceRepository.searchServices(term, colab);

        ServiceDraftRepository serviceDraftRepository = factory.serviceDrafts();
        List<ServiceDraft> serviceDrafts = serviceDraftRepository.searchServices(term, colab);

        return new CatalogServiceSearchResult(catalogs, services, serviceDrafts);
    }

}
