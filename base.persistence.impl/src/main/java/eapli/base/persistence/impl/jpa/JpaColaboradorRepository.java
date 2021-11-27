package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaColaboradorRepository extends JpaAutoTxRepository<Colaborador,Long,Long> implements ColaboradorRepository {


    public JpaColaboradorRepository(){
        super( Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties(),"id");
    }

    public JpaColaboradorRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Colaborador findColaboradorPorNumeroMecanografico(NumeroMecanografico numeroMecanografico){
        TypedQuery<Colaborador> q = createQuery("SELECT c FROM Colaborador c WHERE c.mecanographicNumber = :nm",
                Colaborador.class);
        q.setParameter("nm", numeroMecanografico);
        return q.getSingleResult();
    }


    @Override
    public Iterable<Colaborador> findAllColaboradoresSemTipoEquipa(TipoEquipa t){

        TypedQuery<Colaborador> q = createQuery(
                "SELECT c FROM Colaborador c\n" +
                        "WHERE c.id NOT IN (SELECT c FROM Equipa e\n" +
                        "INNER JOIN e.colaboradores c\n"  +
                        "WHERE e.tipoEquipa = :tipoequipa)\n", Colaborador.class
        );
        q.setParameter("tipoequipa", t);
        return q.getResultList();
    }

    @Override
    public Iterable<Colaborador> findAllResponsaveisSemTipoEquipa(TipoEquipa t){

        TypedQuery<Colaborador> q = createQuery(
                "SELECT c FROM Colaborador c\n" +
                        "WHERE c.id NOT IN (SELECT c FROM Equipa e\n" +
                        "INNER JOIN e.responsaveis c\n"  +
                        "WHERE e.tipoEquipa = :tipoequipa)\n", Colaborador.class
        );
        q.setParameter("tipoequipa", t);
        return q.getResultList();
    }

    @Override
    public Iterable<Colaborador> findAllResponsaveisColaboradoresSemTipoEquipa(TipoEquipa t){
        TypedQuery<Colaborador> q = createQuery(
               "SELECT c FROM Colaborador c\n" +
                       "WHERE c.id NOT IN (SELECT c FROM Equipa e\n" +
                                           "INNER JOIN e.responsaveis c\n"  +
                                           "WHERE e.tipoEquipa = :tipoEquipa)\n" +
                       "AND c.id NOT IN (SELECT c FROM Equipa e\n" +
                                          "INNER JOIN e.colaboradores c\n" +
                                          "WHERE e.tipoEquipa = :tipoEquipa)", Colaborador.class
        );
        q.setParameter("tipoEquipa", t);
        return q.getResultList();
    }

    @Override
    public Colaborador findColaboradorPorEmail(EmailInstitucional emailInstitucional) {
        TypedQuery<Colaborador> q = createQuery("SELECT c FROM Colaborador c WHERE c.emailInstitucional = :emailI",
                Colaborador.class);
        q.setParameter("emailI", emailInstitucional);
        return q.getSingleResult();
    }

    @Override
    public Iterable<Colaborador> findAllColaboradoresPorEquipa(Equipa e) {
        TypedQuery<Colaborador> q = createQuery(
                "SELECT c FROM Equipa e\n" +
                        "INNER JOIN e.colaboradores c\n" +
                        "WHERE e = :equipa", Colaborador.class
        );
        q.setParameter("equipa", e);
        return q.getResultList();
    }

}
