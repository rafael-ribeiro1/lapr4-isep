package daemon.motorfluxo.protocol;

import eapli.base.protocolo.domain.SDP2021Packet;

public abstract class InformationRequest {

    protected String inputRequest;

    protected InformationRequest (final String inputRequest){
        this.inputRequest = inputRequest;
    }

    public abstract SDP2021Packet execute();

    protected SDP2021Packet buildErrorMessage(){
        return new ErrorInRequest(inputRequest).execute();
    }


}
