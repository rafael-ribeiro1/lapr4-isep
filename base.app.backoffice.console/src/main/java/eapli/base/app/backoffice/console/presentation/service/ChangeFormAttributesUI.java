package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChangeFormAttributesUI extends AbstractUI {
    private final AddFormAttributeUI addFromAttributeUI;

    public ChangeFormAttributesUI(Function<AttributeDTO, Void> addAttributeFunction) {
        this.addFromAttributeUI=new AddFormAttributeUI ( addAttributeFunction );
    }

    @Override
    protected boolean doShow() {
        List<AbstractUI> uis=new ArrayList<> (2);
        uis.add(addFromAttributeUI);
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
        return "Changes Attributes";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
