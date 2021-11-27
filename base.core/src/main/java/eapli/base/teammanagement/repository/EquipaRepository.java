package eapli.base.teammanagement.repository;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.DomainRepository;

public interface EquipaRepository extends DomainRepository<Long, Equipa> {
    Iterable<Equipa> findAllEquipasPorTipoEquipa(TipoEquipa t);

    Equipa findEquipaPorCodigoUnico(CodigoUnico codigoUnico);

    Iterable<Equipa> findEquipasColaborador(Colaborador c);
}
