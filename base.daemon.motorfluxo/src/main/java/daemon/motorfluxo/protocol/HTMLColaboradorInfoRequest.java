package daemon.motorfluxo.protocol;

import eapli.base.Application;
import eapli.base.activity.application.HTMLRequestActivityPendingService;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.protocolo.domain.SDP2021Packet;

import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_RES_INFO_DASHBOARD;

public class HTMLColaboradorInfoRequest extends InformationRequest {

    private final RepositoryFactory factory = PersistenceContext.repositories();

    public HTMLColaboradorInfoRequest(final String inputRequest) {
        super(inputRequest);
    }

    @Override
    public SDP2021Packet execute() {

        ColaboradorRepository repository = factory.colaboradores();

        HTMLRequestActivityPendingService requestService = new HTMLRequestActivityPendingService();

        Colaborador colaborador;

        try {
            colaborador = repository.findColaboradorPorNumeroMecanografico(new NumeroMecanografico(this.inputRequest));
        }catch (IllegalArgumentException e){
            return this.buildErrorMessage();
        }


        if(colaborador==null) return this.buildErrorMessage();

        String response = requestService.getResponse(colaborador);

        return new SDP2021Packet(Application.settings().getSdpProtocolVersion(),CODIGO_RES_INFO_DASHBOARD,response);
    }
}
