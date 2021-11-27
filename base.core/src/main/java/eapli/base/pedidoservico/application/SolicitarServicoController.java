package eapli.base.pedidoservico.application;

import eapli.base.Application;
import eapli.base.antlr.formulario.ValidateFormAPI;
import eapli.base.antlr.formulario.ValidateFormListenerAPI;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@UseCaseController
public class SolicitarServicoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    public Iterable<Service> allServices() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        Colaborador colab = colaboradorLogado();

        return factory.services().findWithAccess(colab);
    }

    public Pedido solicitarServico(Service service, Urgencia urgencia, Calendar limiteAprovacao, Calendar dataLimite,
                                   List<byte[]> files, Map<Attribute,String> formResponses) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR);
        Colaborador colab = colaboradorLogado();

        return solicitarServico(colab, service, urgencia, limiteAprovacao, dataLimite, files, formResponses);
    }

    public Pedido solicitarServico(Colaborador colab, Service service, Urgencia urgencia, Calendar limiteAprovacao,
                                   Calendar dataLimite, List<byte[]> files, Map<Attribute,String> formResponses) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.COLABORADOR, BaseRoles.POWER_USER); // For bootstrapping

        if (service.serviceForm().script() != null)
            validateForm(service.serviceForm().script().toString(), formResponses);
        final Pedido pedido = new Pedido(colab, Calendar.getInstance(), service, urgencia, limiteAprovacao, dataLimite,
                files, formResponses);
        final PedidoRepository repo = factory.pedidos();
        Pedido p = repo.save(pedido);
        alertServer(p);
        return p;
    }

    private void alertServer(Pedido pedido) throws IOException {
        SDP2021Packet packet = new SDP2021Packet(Application.settings().getSdpProtocolVersion(),
                SDP2021ProtocolTypeMessages.CODIGO_AVANCAR_FLUXO_PEDIDO, pedido.identity());
        SDP2021Packet response = SDP2021Client.simpleCommunication(packet, Application.settings().getIpEngineServer(),
                Application.settings().getEngineServerPort(), Application.settings().getCertClientName());
        if (response.code() != SDP2021ProtocolTypeMessages.CODIGO_RES_AVANCAR_FLUXO_PEDIDO)
            throw new IllegalArgumentException("Server returned error");
    }

    private void validateForm(String script, Map<Attribute,String> formResponses) {
        ValidateFormAPI api = new ValidateFormListenerAPI(script, formResponses);
        if (!api.executeScript())
            throw new IllegalArgumentException("Respostas de formulário inválidas");
    }

    private Colaborador colaboradorLogado() {
        String email = (authz.session().orElse(null)).authenticatedUser().email().toString();
        Colaborador colab = factory.colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf(email));
        if (colab == null) throw new IllegalArgumentException("Invalid user logged in");
        return colab;
    }

}









