package eapli.base.persistence.impl.inmemory;

import eapli.base.contratoSLAmanagement.domain.ContratoSLA;
import eapli.base.contratoSLAmanagement.repository.ContratoSLARepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryContratoSLARepository extends InMemoryDomainRepository<ContratoSLA,Long> implements ContratoSLARepository {
    static {
        InMemoryInitializer.init();
    }
}
