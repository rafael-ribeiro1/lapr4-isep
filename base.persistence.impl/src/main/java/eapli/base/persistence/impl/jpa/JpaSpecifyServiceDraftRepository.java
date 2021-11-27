package eapli.base.persistence.impl.jpa;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaBaseRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalRepository;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class JpaSpecifyServiceDraftRepository extends BaseJpaRepositoryBase /*JpaTransactionalRepository*/<ServiceDraft,ServiceCode, ServiceCode> implements ServiceDraftRepository {


    public JpaSpecifyServiceDraftRepository() {
        super ( "serviceCode" );
    }
/*
    public JpaSpecifyServiceDraftRepository() {
        super ( "javax.persistence.jdbc.url", "serviceCode" );
    }
*/

    @Override
    public ServiceDraft getServiceById(String id){
        TypedQuery<ServiceDraft> q =  entityManager().createQuery(
                "SELECT d FROM ServiceDraft d WHERE  d.serviceCode =:serviceCode",ServiceDraft.class);
        q.setParameter("serviceCode", id);
        return q.getSingleResult ();
    }


    @Override
    public Optional<ServiceDraft> ofIdentity(ServiceCode id) {
        TypedQuery<ServiceDraft> q =  entityManager().createQuery(
                "SELECT d FROM ServiceDraft d WHERE  d.serviceCode =:serviceCode",ServiceDraft.class);
        q.setParameter("serviceCode", id);

        final Map<String, Object> params = new HashMap<> ();
        params.put("id", id);
        return matchOne("serviceCode = :id", params);
    }


    @Override
    public boolean containsOfIdentity(ServiceCode id) {
        return ServiceDraftRepository.super.containsOfIdentity ( id );
    }

    @Override
    public boolean contains(ServiceDraft entity) {
        return ServiceDraftRepository.super.contains ( entity );
    }

    @Override
    public List<ServiceDraft> searchServices(String term, Colaborador colab) {
        TypedQuery<ServiceDraft> q = createQuery(
                "SELECT s FROM ServiceDraft s " +
                     "JOIN s.catalog.accessAllowed e " +
                     "JOIN e.colaboradores m " +
                     "JOIN e.responsaveis r " +
                     "WHERE (:colab = m " +
                     "OR :colab = r) " +
                     "AND (LOWER(s.title.title) LIKE :term " +
                     "OR LOWER(s.briefDescription.briefText) LIKE :term " +
                     "OR LOWER(s.completeDescription.completeText) LIKE :term)", ServiceDraft.class
        );
        q.setParameter("term", "%"+term.toLowerCase()+"%");
        q.setParameter("colab", colab);
        return q.getResultList();
    }

    @Override
    public void delete(ServiceDraft entity) {
        //entity.setDeleted ();
        super.delete ( entity );
    }
}
