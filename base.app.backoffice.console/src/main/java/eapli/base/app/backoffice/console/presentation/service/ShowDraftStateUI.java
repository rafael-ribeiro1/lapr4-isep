package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ShowDraftStateUI extends AbstractUI {

    private final SpecifyServiceUIControllerFacade facade;

    public ShowDraftStateUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        facade.printStatus ();
        Console.readLine ("Press Enter to continue");
        return true;
    }

    @Override
    public String headline() {
        return "Show Service Information";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
