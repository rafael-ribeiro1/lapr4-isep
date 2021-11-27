package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class ServiceFillOutFormUI extends AbstractUI{
    private final ChangeFormNameUI changeFormNameUI;
    private final ChangeFormAttributesUI changeFormAttributesUI;

    public ServiceFillOutFormUI(SpecifyServiceUIControllerFacade facade) {
        this.changeFormNameUI=new ChangeFormNameUI ( facade::addFillOutFormName );
        this.changeFormAttributesUI=new ChangeFormAttributesUI ( facade::addFillOutFormAttribute );
    }

    @Override
    protected boolean doShow() {
        List<AbstractUI> uis=new ArrayList<> (2);
        uis.add ( changeFormNameUI );
        uis.add ( changeFormAttributesUI );
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
        return "Fill Out Form";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
