package eapli.base.teammanagement.repository;

import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface TipoEquipaRepository extends DomainRepository<Long, TipoEquipa> {
    Optional<TipoEquipa> findTipoEquipaPorCodigoUnico(CodigoUnico codigo);
}
