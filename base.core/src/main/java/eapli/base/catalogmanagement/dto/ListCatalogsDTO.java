package eapli.base.catalogmanagement.dto;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class ListCatalogsDTO {

    private static final AuthorizationService authz = AuthzRegistry.authorizationService();


    private final CatalogRepository repository;

    public ListCatalogsDTO (final CatalogRepository repository){
        this.repository = repository;
    }

    public List<CatalogDTO> allCatalogDTO (){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_SH,BaseRoles.POWER_USER);
        Iterable<Catalog> allCatalogs= repository.findAll();
        List<CatalogDTO> catalogDTO = new ArrayList<>();
        for(Catalog c : allCatalogs){
            catalogDTO.add(c.toDTO());
        }
        return catalogDTO;
    }
}
