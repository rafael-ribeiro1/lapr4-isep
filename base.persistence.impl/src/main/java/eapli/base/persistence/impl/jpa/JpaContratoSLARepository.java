package eapli.base.persistence.impl.jpa;

import eapli.base.contratoSLAmanagement.domain.ContratoSLA;
import eapli.base.contratoSLAmanagement.repository.ContratoSLARepository;

public class JpaContratoSLARepository extends BaseJpaRepositoryBase<ContratoSLA,Long,Long> implements ContratoSLARepository {

    public JpaContratoSLARepository(){ super("id");}
}
