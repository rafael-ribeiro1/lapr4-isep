package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.CatalogIdentifier;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCatalogRepository
        extends InMemoryDomainRepository<Catalog, Long>
        implements CatalogRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Catalog> searchCatalogs(String term, Colaborador colab) {
        List<Catalog> catalogs = new ArrayList<>();
        for (Catalog c : findAll()) {
            if (c.containsSearchTerm(term))
                catalogs.add(c);
        }
        return catalogs;
    }
}
