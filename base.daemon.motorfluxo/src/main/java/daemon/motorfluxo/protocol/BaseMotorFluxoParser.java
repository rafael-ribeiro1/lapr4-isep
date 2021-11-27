package daemon.motorfluxo.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;

public abstract class BaseMotorFluxoParser {

    protected BaseMotorFluxoParser(){}

    public abstract SDP2021Packet parse (final SDP2021Packet inputPacket);

    protected static SDP2021Packet errorPacket(String response){
        return new ErrorInRequest(response).execute();
    }

}
