package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.colaboratormanagement.application.AddFuncaoController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.framework.actions.Action;
public class FuncaoBootstrapper implements Action {

    private final AddFuncaoController controller = new AddFuncaoController();

    private static final Logger LOGGER = LogManager.getLogger(FuncaoBootstrapper.class);


    /**
     * Perform the desired action.
     *
     * @return true to signal OK; false otherwise, e.g., signal an error
     */
    @Override
    public boolean execute() {
        register("Executivo");
        register("CEO");
        register("Cozinheiro");
        register("Limpa-Pratos");
        register("Developer");
        register("Estagiario");
        register("Chefe");
        register("Candido");
        register("Mascote");
        register("Kanye West");
        register("Vozes na cabeca");
        return false;
    }

    private void register(String name){

        try {
            controller.addFuncao(name);
            LOGGER.info("Registado funcao com o nome: " +name);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", e);
        }

    }
}
