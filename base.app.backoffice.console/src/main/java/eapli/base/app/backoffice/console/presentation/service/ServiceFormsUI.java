package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ServiceFormsUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;
    private final ServiceFillOutFormUI fillOutUI;
    private final ServiceFeedbackFormUI feedbackUI;

    public ServiceFormsUI(final SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
        this.fillOutUI=new ServiceFillOutFormUI (facade);
        this.feedbackUI=new ServiceFeedbackFormUI (facade);
    }

    @Override
    protected boolean doShow() {
        List<AbstractUI> uis=new ArrayList<> (2);
        uis.add ( fillOutUI );
        uis.add ( feedbackUI );
        AbstractUI ui;
        do{
            SingleSelectionWidget<AbstractUI> singleSelect=new SingleSelectionWidget<> ( uis );
            singleSelect.doSelection ();
            ui=singleSelect.selectedItem ();
            if(ui!=null)
                ui.show ();
        }while(ui!=null);
        return true;
    }

    @Override
    public String headline() {
        return "Service Forms";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
