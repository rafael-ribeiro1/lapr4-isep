package eapli.base.persistence.impl.inmemory;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCriticidadeRepository
        extends InMemoryDomainRepository<Criticidade,Long>
        implements CriticidadeRepository {

    static {
        InMemoryInitializer.init();
    }


}
