package daemon.motorfluxo.protocol;

import daemon.motorfluxo.application.InitiateFluxController;
import daemon.motorfluxo.application.InitiateFluxControllerImpl;
import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.*;
import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_AVANCAR_FLUXO_PEDIDO;

class MotorFluxoParserV0 extends BaseMotorFluxoParser {

    public MotorFluxoParserV0() {
    }

    @Override
    public SDP2021Packet parse(SDP2021Packet inputPacket) {
        String response = inputPacket.response();
        SDP2021Packet requestPacket = this.errorPacket(response);

        switch (inputPacket.code()) {
            case CODIGO_TESTE:
                InformationRequest testeCode = new TestRequest(response);
                requestPacket = testeCode.execute();
                break;
            case CODIGO_FIM:
                InformationRequest endRequest = new EndRequest(response);
                requestPacket = endRequest.execute();
                break;
            case CODIGO_INFO_DASHBOARD:
                InformationRequest infoRequest = new HTMLColaboradorInfoRequest(response);
                requestPacket = infoRequest.execute();
                break;
            case CODIGO_AVANCAR_FLUXO_PEDIDO:
                int version=inputPacket.version ();
                InitiateFluxController controller=new InitiateFluxControllerImpl();
                BaseFluxRequest request= new ManageFluxRequestV0( controller,response );
                requestPacket =request.execute ();
                break;
            default:
                requestPacket = new ErrorInRequest(response).execute();
                break;
        }


        return requestPacket;
    }
}
