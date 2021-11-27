package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryEquipaRepository extends InMemoryDomainRepository<Equipa, Long> implements EquipaRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Equipa> findAllEquipasPorTipoEquipa(TipoEquipa t) {
        return match(e -> e.tipoEquipa().equals(t));
    }

    @Override
    public Equipa findEquipaPorCodigoUnico(CodigoUnico cod) {
        Optional<Equipa> equipa = matchOne(e -> e.codigoUnico().equals(cod));
        return equipa.orElse(null);
    }

    @Override
    public Iterable<Equipa> findEquipasColaborador(Colaborador c) {
        return match(equipa -> equipa.colaboradores().contains(c) || equipa.responsaveis().contains(c));
    }
}
