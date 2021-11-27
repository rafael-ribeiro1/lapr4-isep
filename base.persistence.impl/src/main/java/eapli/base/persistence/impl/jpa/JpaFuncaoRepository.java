package eapli.base.persistence.impl.jpa;

import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;

public class JpaFuncaoRepository
        extends BaseJpaRepositoryBase<Funcao, Long, Long>
        implements FuncaoRepository {

    public JpaFuncaoRepository() {
        super("id");
    }

}
