package eapli.base.persistence.impl.jpa;

import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.TipoEquipaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaTipoEquipaRepository extends BaseJpaRepositoryBase<TipoEquipa, Long, Long> implements TipoEquipaRepository {

    JpaTipoEquipaRepository() {
        super("id");
    }

    @Override
    public Optional<TipoEquipa> findTipoEquipaPorCodigoUnico(CodigoUnico codigo){
        final Map<String, Object> params = new HashMap<>();
        params.put("c", codigo);
        return matchOne("codigoUnico = :c", params);
    }
}
