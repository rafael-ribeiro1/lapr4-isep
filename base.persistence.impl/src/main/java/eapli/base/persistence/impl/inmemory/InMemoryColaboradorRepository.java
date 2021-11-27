package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.ArrayList;
import java.util.List;

public class InMemoryColaboradorRepository extends InMemoryDomainRepository<Colaborador,Long> implements ColaboradorRepository {
    static {
        InMemoryInitializer.init();
    }


    @Override
    public Colaborador findColaboradorPorNumeroMecanografico(NumeroMecanografico numeroMecanografico) {
        java.util.Optional<Colaborador> opt = matchOne(colaborador -> colaborador.mecanographicNumber().equals(numeroMecanografico));
        return opt.orElse(null);
    }

    @Override
    public Iterable<Colaborador> findAllColaboradoresSemTipoEquipa(TipoEquipa t) {
        Iterable<Equipa> it = PersistenceContext.repositories().equipas().findAll();
        List<Colaborador> col = new ArrayList<>();
        for (Equipa e : it){
            if (!e.tipoEquipa().equals(t))
                col.addAll(e.colaboradores());
        }
        return col;
    }

    @Override
    public Iterable<Colaborador> findAllResponsaveisSemTipoEquipa(TipoEquipa t){
        Iterable<Equipa> it = PersistenceContext.repositories().equipas().findAll();
        List<Colaborador> col = new ArrayList<>();
        for (Equipa e : it){
            if (!e.tipoEquipa().equals(t))
                col.addAll(e.responsaveis());
        }
        return col;
    }

    @Override
    public Iterable<Colaborador> findAllResponsaveisColaboradoresSemTipoEquipa(TipoEquipa t) {
       List<Colaborador> l = new ArrayList<>();
       l.addAll((List<Colaborador>)findAllResponsaveisSemTipoEquipa(t));
       l.addAll((List<Colaborador>)findAllColaboradoresSemTipoEquipa(t));
       return l;
    }

    @Override
    public Colaborador findColaboradorPorEmail(EmailInstitucional emailInstitucional) {
        java.util.Optional<Colaborador> opt = matchOne(colaborador -> colaborador.emailInstitucional().equals(emailInstitucional));
        return opt.orElse(null);
    }


    @Override
    public Iterable<Colaborador> findAllColaboradoresPorEquipa(Equipa e) {
        return e.colaboradores();
    }
}
