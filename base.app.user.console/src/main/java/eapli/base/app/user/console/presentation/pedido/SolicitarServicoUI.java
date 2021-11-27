package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.common.console.presentation.file.FilesChooser;
import eapli.base.app.common.console.presentation.form.FillFormWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.pedidoservico.application.SolicitarServicoController;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.util.*;

public class SolicitarServicoUI extends AbstractUI {

    private final SolicitarServicoController controller = new SolicitarServicoController();

    @Override
    protected boolean doShow() {
        Iterable<Service> services;
        try {
            services = controller.allServices();
        } catch (NullPointerException e) {
                System.out.println("Error: no user logged in");
                return false;
        }
        Service service = selectService(services);
        Urgencia urgencia = selectUrgency();
        Calendar limiteAprovacao = null;
        if (service.activityFlow().atividadeAprovacao() != null)
            limiteAprovacao = Console.readCalendar("Approval limit date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
        Calendar dataLimite = pickLimitDate(limiteAprovacao);

        FilesChooser fc = new FilesChooser();
        List<byte[]> files = fc.chooseFiles();

        List<Attribute> attributes = service.serviceForm().attributes();
        Map<Attribute,String> formResponses = FillFormWidget.fillForm("=== Fill Service Form ===", attributes);

        if(!Console.readBoolean("Confirm registration (y/n)  ?")) return false;

        try {
            this.controller.solicitarServico(service, urgencia, limiteAprovacao, dataLimite, files, formResponses);
            System.out.printf("Successfully requested service '%s'%n", service.identity().toString());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error requesting service");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while alerting the engine server");
        }

        return false;
    }

    private Service selectService(Iterable<Service> services) {
        System.out.println("--- Select service ---");
        SingleSelectionWidget<Service> servSelect = new SingleSelectionWidget<>(services, true);
        servSelect.doSelection();
        return servSelect.selectedItem();
    }

    private Urgencia selectUrgency() {
        Iterable<Urgencia> urgencias = Pedido.urgencias();
        System.out.println("--- Select urgency ---");
        SingleSelectionWidget<Urgencia> urgSelect = new SingleSelectionWidget<>(urgencias, true);
        urgSelect.doSelection();
        return urgSelect.selectedItem();
    }

    private Calendar pickLimitDate(Calendar limiteAprovacao) {
        Calendar dataLimite;
        do {
            dataLimite = Console.readCalendar("Realization limit date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
        } while (limiteAprovacao != null && dataLimite.compareTo(limiteAprovacao) <= 0);
        return dataLimite;
    }

    @Override
    public String headline() {
        return "Request service";
    }

}
