package eapli.base.criticidademanagement.application;

import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
public class ListCriticidadesService {

    private final CriticidadeRepository repository = PersistenceContext.repositories().criticidades();

    public Iterable<Criticidade> listCriticidades (){
        return this.repository.findAll();
    }

}
