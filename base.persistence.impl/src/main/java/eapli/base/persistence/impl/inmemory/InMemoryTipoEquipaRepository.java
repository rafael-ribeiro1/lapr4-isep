package eapli.base.persistence.impl.inmemory;

import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryTipoEquipaRepository extends InMemoryDomainRepository<TipoEquipa, Long>  implements TipoEquipaRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<TipoEquipa> findTipoEquipaPorCodigoUnico(CodigoUnico codigo){
        return matchOne(tipoEquipa -> tipoEquipa.codigoUnico().equals(codigo));
    }
}
