package eapli.base.persistence.impl.jpa;

import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;

public class JpaCriticidadeRepository extends BaseJpaRepositoryBase<Criticidade,Long,Long>
    implements CriticidadeRepository {

    public  JpaCriticidadeRepository(){super("id");}

}
