package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class CreateActivityFlowUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;
    private static final String ATIVIDADE_APROVACAO = "Approval Activity and Realization Activity";
    private static final String ATIVIDADE_REALIZACAO = "Realization Activity";

    private SpecifyAprovationActivityUI aprovationActivityUI;

    private SpecifyRealizationActivityUI realizationActivityUI;

    public CreateActivityFlowUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
        realizationActivityUI= new SpecifyRealizationActivityUI(facade);
    }


    @Override
    protected boolean doShow() {
        try{
            List<String> options = new ArrayList<>();
            options.add(ATIVIDADE_APROVACAO);
            options.add(ATIVIDADE_REALIZACAO);

            System.out.println("Select Activity's Flow Structure:");
            SingleSelectionWidget<String> chooseTypeActivity = new SingleSelectionWidget<>(options, true);
            chooseTypeActivity.doSelection();

            if (chooseTypeActivity.selectedItem().equals(ATIVIDADE_APROVACAO)){
                aprovationActivityUI = new SpecifyAprovationActivityUI(facade);
                aprovationActivityUI.doShow();
            }
            realizationActivityUI.doShow();

            boolean confirm = Console.readBoolean("Confirm the register of flow Activity (y/n)");

            if(!confirm)return false;
            System.out.println("Activity flow created with success");
        }catch ( IllegalArgumentException|ConcurrencyException e ){
            System.out.println (e.getMessage ());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Activity Flow";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
