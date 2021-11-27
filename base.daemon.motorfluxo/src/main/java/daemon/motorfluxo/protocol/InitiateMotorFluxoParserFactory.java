package daemon.motorfluxo.protocol;

import daemon.motorfluxo.presentation.MotorFluxoParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitiateMotorFluxoParserFactory {
    private static final Logger LOGGER = LogManager.getLogger(InitiateMotorFluxoParserFactory.class);

    public static BaseMotorFluxoParser create(int version){
        switch (version){
            case 1:
                return new MotorFluxoParserV1();
            case 0:
                return new MotorFluxoParserV0();
            default:
                throw new IllegalArgumentException("Invalid version !");

        }
    }


}
