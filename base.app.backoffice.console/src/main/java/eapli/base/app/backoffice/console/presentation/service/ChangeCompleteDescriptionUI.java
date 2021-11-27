package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.servicemanagement.domain.CompleteDescription;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeCompleteDescriptionUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeCompleteDescriptionUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        System.out.println ("Current Complete Description: "+facade.getCompleteDescription ());
        String description= Console.readLine ("Insert Complete Description (Limit "+ CompleteDescription.COMPLETE_DESCRIPTION_LENGTH +" characters)" );
        try {
            facade.setCompleteDescription(description);
            System.out.println ("Complete Description Inserted");
        } catch (IllegalArgumentException | ConcurrencyException e) {
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Complete Description";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
