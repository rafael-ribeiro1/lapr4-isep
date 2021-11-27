package eapli.base.app.user.console.presentation.activity;

import eapli.base.activity.application.RealizarAtividadeController;
import eapli.base.activity.domain.RealizacaoManual;
import eapli.base.activity.domain.TipoAtividadeManual;
import eapli.base.app.common.console.presentation.form.FillFormWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.pedidoservico.domain.Pedido;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RealizarAtividadeUI extends AbstractUI {

    private final RealizarAtividadeController controller = new RealizarAtividadeController();

    private static final int APPROVAL_ACTIVITY = 1;
    private static final int REALIZATION_ACTIVITY = 2;

    @Override
    protected boolean doShow() {
        int type = typeOfActivity();
        List<Pedido> activities = activitiesOfType(type);
        if (activities == null) return false;

        Pedido selected = selectActivity(activities);
        if (selected == null) return false;
        System.out.println(selected.details());

        waitForRealization();

        List<Attribute> attributes = formOfActivity(type, selected);
        if (attributes == null) return false;
        Map<Attribute, String> formResponses = FillFormWidget.fillForm("=== Fill Activity Form ===", attributes);

        if(!Console.readBoolean("Confirm realization (y/n)  ?")) return false;

        TipoAtividadeManual tipoAtividade = toTypeOfActivity(type);
        if (tipoAtividade == null) return false;

        try {
            this.controller.realizarTarefa(selected, tipoAtividade, formResponses);
            System.out.println("Successfully registered realization of activity");
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error registering realization");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while alerting the engine server");
        }

        return false;
    }

    private int typeOfActivity() {
        int type;
        do {
            type = Console.readInteger("Select activity type: (1 - Approval ; 2 - Realization)");
        } while (type != APPROVAL_ACTIVITY && type != REALIZATION_ACTIVITY);
        return type;
    }

    private List<Pedido> activitiesOfType(int type) {
        if (type == APPROVAL_ACTIVITY)
            return controller.approvalActivities();
        else if (type == REALIZATION_ACTIVITY)
            return controller.realizationActivities();
        else
            return null;
    }

    private Pedido selectActivity(List<Pedido> activities) {
        System.out.println("--- Select activity (of request) ---");
        SingleSelectionWidget<Pedido> selector = new SingleSelectionWidget<>(activities, true);
        selector.doSelection();
        return selector.selectedItem();
    }

    private void waitForRealization() {
        String res;
        do {
            res = Console.readLine("Press 'y' to continue realization of activity...");
        } while (!res.equalsIgnoreCase("y"));
    }

    private List<Attribute> formOfActivity(int type, Pedido pedido) {
        if (type == APPROVAL_ACTIVITY)
            return pedido.fluxoAtividades().atividadeAprovacao().attributes();
        else if (type == REALIZATION_ACTIVITY)
            try {
                return ((RealizacaoManual)pedido.fluxoAtividades().atividadeRealizacao()).attributes();
            } catch (ClassCastException e) {
                return null;
            }
        else
            return null;
    }

    private TipoAtividadeManual toTypeOfActivity(int type) {
        if (type == APPROVAL_ACTIVITY)
            return TipoAtividadeManual.APROVACAO;
        else if (type == REALIZATION_ACTIVITY)
            return TipoAtividadeManual.REALIZACAO;
        else
            return null;
    }

    @Override
    public String headline() {
        return "Do activity";
    }

}
