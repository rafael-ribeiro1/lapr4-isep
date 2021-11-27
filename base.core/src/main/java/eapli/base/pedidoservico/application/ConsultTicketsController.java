package eapli.base.pedidoservico.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidoservico.dto.PedidoDTO;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.Collection;

public class ConsultTicketsController {
    private final Role ROLE=BaseRoles.COLABORADOR;
    private final AuthorizationService authz= AuthzRegistry.authorizationService ();
    private final PedidoRepository ticketRepository= PersistenceContext.repositories ().pedidos ();
    private final SystemUser user=(authz.session ().orElseThrow ()).authenticatedUser ();

    public void getUserTickets(Collection<PedidoDTO> pendingTicketsDTO,Collection<PedidoDTO> concludedTicketsDTO ){
        authz.ensureAuthenticatedUserHasAnyOf(ROLE);
        ticketRepository.getTicketsOrderedByMostRecent (user,pendingTicketsDTO,concludedTicketsDTO);
    }
}
