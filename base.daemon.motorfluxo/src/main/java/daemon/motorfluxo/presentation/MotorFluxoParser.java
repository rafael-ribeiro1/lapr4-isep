package daemon.motorfluxo.presentation;
import daemon.motorfluxo.protocol.*;
import eapli.base.protocolo.domain.SDP2021Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class MotorFluxoParser {

    private static final Logger LOGGER = LogManager.getLogger(MotorFluxoParser.class);

    public static SDP2021Packet parse(final SDP2021Packet inputPacket) {
        BaseMotorFluxoParser parser = InitiateMotorFluxoParserFactory.create(inputPacket.version());
        return parser.parse(inputPacket);
    }

}
