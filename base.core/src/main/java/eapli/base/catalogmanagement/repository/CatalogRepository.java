package eapli.base.catalogmanagement.repository;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.CatalogIdentifier;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface CatalogRepository  extends DomainRepository<Long, Catalog> {

    List<Catalog> searchCatalogs(String term, Colaborador colab);

}
