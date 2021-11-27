package eapli.base.colaboratormanagement.application.viaDTO;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ListCollaboratorDTOService {

    private static final AuthorizationService authz = AuthzRegistry.authorizationService();

    private static final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

    public static Iterable<ColaboradorDTO> allColaborators(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,BaseRoles.GESTOR_SH,BaseRoles.RESPONSAVEL_RH);

        final Iterable<Colaborador> colaborador = colaboradorRepository.findAll();

        final List<ColaboradorDTO> ret = new ArrayList<>();
        colaborador.forEach(e->ret.add(e.toDTO()));
        return ret;
    }

    public static List<ColaboradorDTO> convertListToColaboratorDTO (Iterable<Colaborador> listaColaboradores){
        List<ColaboradorDTO> converted = new ArrayList<>();

        for(Colaborador co : listaColaboradores ){
            converted.add(co.toDTO());
        }

        return converted;
    }

}
