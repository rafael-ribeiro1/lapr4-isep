package eapli.base.teammanagement.application;

import eapli.base.common.Cor;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegistarTipoEquipaController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TipoEquipaRepository tipoEquipaRepository = PersistenceContext.repositories().tiposEquipa();

    public TipoEquipa registarTipoEquipa(final Cor cor, final String codigoUnico, final String descricao){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH, BaseRoles.POWER_USER);

        final TipoEquipa newTipoEquipa = new TipoEquipa(cor, new CodigoUnico(codigoUnico), Description.valueOf(descricao));
        return  tipoEquipaRepository.save(newTipoEquipa);

    }
}
