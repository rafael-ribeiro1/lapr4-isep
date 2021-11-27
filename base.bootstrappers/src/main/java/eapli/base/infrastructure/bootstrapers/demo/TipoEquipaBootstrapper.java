package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.teammanagement.application.RegistarTipoEquipaController;
import eapli.base.common.Cor;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TipoEquipaBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(TipoEquipaBootstrapper.class);

    private final RegistarTipoEquipaController controller = new RegistarTipoEquipaController();


    @Override
    public boolean execute() {
        register(Cor.valueOf(178, 17, 32), "DCOM1", "Departamento comercial");
        register(Cor.valueOf(109, 137, 201), "DRH1", "Departamento de recursos humanos");
        register(Cor.valueOf(141, 129, 129), "DFIN1", "Departamento financeiro");
        return true;
    }

    private void register(Cor cor, String codigoUnico,String  descricao) {
        try {
            controller.registarTipoEquipa(cor, codigoUnico, descricao);
            LOGGER.info(codigoUnico);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", codigoUnico);
            LOGGER.trace("Assuming existing record", e);
        }
    }

}
