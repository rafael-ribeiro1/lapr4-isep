package eapli.base.contratoSLAmanagement.repository;


import eapli.base.contratoSLAmanagement.domain.ContratoSLA;
import eapli.framework.domain.repositories.DomainRepository;

public interface ContratoSLARepository extends DomainRepository<Long,ContratoSLA> {
    Iterable<ContratoSLA> findAll();
}
