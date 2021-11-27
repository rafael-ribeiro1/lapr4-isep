package eapli.base.persistence.impl.jpa;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.CatalogIdentifier;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.colaboratormanagement.domain.Colaborador;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaCatalogRepository
        extends BaseJpaRepositoryBase<Catalog, Long, Long>
        implements CatalogRepository {

    public JpaCatalogRepository() {
        super("id");
    }


    @Override
    public List<Catalog> searchCatalogs(String term, Colaborador colab) {
        TypedQuery<Catalog> q = createQuery(
                "SELECT c FROM Catalog c " +
                "JOIN c.accessAllowed e " +
                "JOIN e.colaboradores m " +
                "JOIN e.responsaveis r " +
                "WHERE (:colab = m " +
                "OR :colab = r) " +
                "AND (LOWER(c.title.title) LIKE :term " +
                "OR LOWER(c.briefDesc.briefDesc) LIKE :term " +
                "OR LOWER(c.completeDesc.completeDesc) LIKE :term)", Catalog.class
        );
        q.setParameter("term", "%"+term.toLowerCase()+"%");
        q.setParameter("colab", colab);
        return q.getResultList();
    }
}
