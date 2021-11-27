package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.icon.IconChooser;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeIconUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeIconUI(SpecifyServiceUIControllerFacade facade) {
        this.facade=facade;
    }

    @Override
    protected boolean doShow() {
        byte[] icon = new IconChooser (true).chooseIcon ();
        try{
            facade.setIcon ( icon );
            System.out.println ("Icon selected");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Icon";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
