
package daemon.motorfluxo;
import daemon.motorfluxo.presentation.MotorFluxoServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;

import java.util.logging.Level;

/**
 * eCafeteria Booking daemon.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
@SuppressWarnings("squid:S106")
public final class MotorFluxoDaemon {

    private static final int LOCAL_PORT = 32507;
    private static final Logger LOGGER = LogManager.getLogger(MotorFluxoDaemon.class);

    /**
     * Avoid instantiation of this class.
     */
    private MotorFluxoDaemon() {
    }

    public static void main(final String[] args) {



        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());






        LOGGER.info("Starting the server socket");
        try {

            MotorFluxoServer.startServer ();
        }catch (Exception e){
            LOGGER.error ("Error while connecting to the socket");
            e.printStackTrace ();
        }

        LOGGER.info("Exiting the daemon");
    }
}
