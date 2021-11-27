package eapli.base.catalogmanagement.application;


import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.catalogmanagement.dto.ListCatalogsDTO;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;


@UseCaseController
public class AssociarCriticidadeCatalogoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    private final CatalogRepository catalogRepository = factory.catalogs();

    private final CriticidadeRepository criticidadeRepository = factory.criticidades();

    public Catalog associarCriticidadeCatalogo(CatalogDTO selectedCatalog, Criticidade selectedCriticidade) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,BaseRoles.GESTOR_SH);
        Catalog obtainedCatalog = catalogRepository.ofIdentity(selectedCatalog.id).get();
        obtainedCatalog.defineCriticidade(selectedCriticidade);
        catalogRepository.save(obtainedCatalog);
        return obtainedCatalog;
    }

    public Criticidade defineNewObjectives(Criticidade criticidade,final int temMaxApro,final int temAvgAprova,
                                           final int tempMaxReso,final int tempAvgReso){
        Criticidade newCriticidade = criticidade.defineNewObjectives(temMaxApro,temAvgAprova,tempMaxReso,tempAvgReso);
        criticidadeRepository.save(newCriticidade);
        return newCriticidade;
    }


    public Iterable<CatalogDTO> getCatalogos(){
        ListCatalogsDTO catalogsDTO = new ListCatalogsDTO(catalogRepository);
        return catalogsDTO.allCatalogDTO();
    }

    public Iterable<Criticidade> getCriticidades(){
        return criticidadeRepository.findAll();
    }



}
