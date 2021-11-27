package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFuncaoRepository
        extends InMemoryDomainRepository<Funcao, Long>
        implements FuncaoRepository {

    static {
        InMemoryInitializer.init();
    }

}
