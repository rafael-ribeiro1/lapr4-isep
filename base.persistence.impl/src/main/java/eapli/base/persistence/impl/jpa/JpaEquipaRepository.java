package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import javax.persistence.TypedQuery;


public class JpaEquipaRepository extends JpaAutoTxRepository<Equipa,Long, Long> implements EquipaRepository {

    public JpaEquipaRepository(TransactionalContext autoTx){
         super(autoTx,"id");
    }

    public JpaEquipaRepository() {
        super(Application.settings().getPersistenceUnitName(),Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Equipa> findAllEquipasPorTipoEquipa(TipoEquipa t) {
        TypedQuery<Equipa> q = createQuery(
                "SELECT e FROM Equipa e WHERE  e.tipoEquipa =:tipoEquipa",Equipa.class);
        q.setParameter("tipoEquipa", t);
        return q.getResultList();
    }

    @Override
    public Equipa findEquipaPorCodigoUnico(CodigoUnico codigoUnico) {
        TypedQuery<Equipa> q = createQuery("SELECT e FROM Equipa e WHERE e.codigoUnico = :cod",
                Equipa.class);
        q.setParameter("cod", codigoUnico);
        return q.getSingleResult();
    }


    @Override
    public Iterable<Equipa> findEquipasColaborador(Colaborador c){
        TypedQuery<Equipa> q = createQuery("SELECT e FROM Equipa e\n" +
                "WHERE :c member e.colaboradores OR  :c member e.responsaveis", Equipa.class);
        q.setParameter("c", c);
        return q.getResultList();
    }

}
