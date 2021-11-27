package daemon.executoratividade;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.utils.ANSI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.base.infrastructure.persistence.PersistenceContext;
import daemon.executoratividade.presentation.ExecutorAtividadeServer;

public class ExecutorAtividadeDaemon {

    private static final int LOCAL_PORT = 32507;
    private static final Logger LOGGER = LogManager.getLogger(ExecutorAtividadeDaemon.class);

    /**
     * Avoid instantiation of this class.
     */
    private ExecutorAtividadeDaemon() {
    }

    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories ().users (),
                new BasePasswordPolicy(), new PlainTextEncoder());

        LOGGER.info("Starting the server socket");
        try {

            ExecutorAtividadeServer.startServer ();
        }catch (Exception e){
            LOGGER.info("Error while connecting to the socket");
            e.printStackTrace ();
        }

        LOGGER.info("Exiting the daemon");
        System.exit(0);
    }
}
