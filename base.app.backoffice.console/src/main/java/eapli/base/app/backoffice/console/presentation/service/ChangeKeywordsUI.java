package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class ChangeKeywordsUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    private final AddKeywordUI addKeywordUI;
    private final RemoveKeywordUI removeKeywordUI;

    public ChangeKeywordsUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
        this.addKeywordUI=new AddKeywordUI (facade);
        this.removeKeywordUI=new RemoveKeywordUI ( facade );
    }

    @Override
    protected boolean doShow() {
        List<AbstractUI> uis=new ArrayList<> (2);
        uis.add(addKeywordUI);
        uis.add(removeKeywordUI);
        AbstractUI ui;
        do{
            System.out.println ("Current Keywords: ");
            facade.printKeywords();
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
        return "Keywords";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
