package eapli.base.persistence.impl.jpa;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.State;
import eapli.base.servicemanagement.repository.ServiceRepository;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaSpecifyServiceRepository extends BaseJpaRepositoryBase<Service, ServiceCode, ServiceCode> implements ServiceRepository {

    JpaSpecifyServiceRepository() {
        super ( "serviceCode" );
    }

    @Override
    public Optional<Service> findById(ServiceCode id) {
        return super.findById ( id );
    }

    @Override
    public List<Service> getAllUnpublishedServices() {
        return getAllServicesExceptState ( State.PUBLISHED );
    }

    @Override
    public List<Service> getAllServicesExceptState(State state){
        TypedQuery<Service> q= createQuery (
                "SELECT p FROM Service p " +
                        " where p.state not like :term",Service.class
        );
        q.setParameter ( "term", state);
        return q.getResultList ();
    }

    @Override
    public void publishService(String id) {
        changeServiceState ( id, State.PUBLISHED);
    }

    public void changeServiceState(final String id, State state){
        EntityTransaction tx= entityManager ().getTransaction ();
        tx.begin ();
        Query q=entityManager ().createQuery (
              "UPDATE Service p " +
                      " SET p.state = :newState " +
                      "where p.serviceCode= :id"
        );
        q.setParameter ( "newState", state);
        q.setParameter ( "id",new ServiceCode ( id ) );
        q.executeUpdate ();
        tx.commit ();
    }

    @Override
    public List<Service> searchServices(String term, Colaborador colab) {
        TypedQuery<Service> q = createQuery(
                "SELECT DISTINCT s FROM Service s " +
                     "JOIN s.lKeywords k " +
                     "JOIN s.catalog.accessAllowed e " +
                     "JOIN e.colaboradores m " +
                     "JOIN e.responsaveis r " +
                     "WHERE (:colab = m " +
                     "OR :colab = r) " +
                     "AND (LOWER(s.title.title) LIKE :term " +
                     "OR LOWER(s.briefDescription.briefText) LIKE :term " +
                     "OR LOWER(s.completeDescription.completeText) LIKE :term " +
                     "OR LOWER(k.keyword) LIKE :term)", Service.class
        );
        q.setParameter("term", "%"+term.toLowerCase()+"%");
        q.setParameter("colab", colab);
        return q.getResultList();
    }

    @Override
    public List<Service> findWithAccess(Colaborador colab) {
        TypedQuery<Service> q = createQuery(
                "SELECT s FROM Service s " +
                   "JOIN s.catalog.accessAllowed t " +
                   "JOIN t.responsaveis r " +
                   "JOIN t.colaboradores m " +
                   "WHERE r = :colab OR m = :colab", Service.class);
        q.setParameter("colab", colab);
        return q.getResultList();
    }
}
