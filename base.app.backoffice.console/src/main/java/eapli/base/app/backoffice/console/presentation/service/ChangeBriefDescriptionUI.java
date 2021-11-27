package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.servicemanagement.domain.BriefDescription;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeBriefDescriptionUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeBriefDescriptionUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        System.out.println ("Current Brief Description: "+facade.getBriefDescription ());
        String description= Console.readLine ("Insert Brief Description (Limit "+ BriefDescription.BRIEF_DESCRIPTION_LENGTH +" characters)" );
        try{
            facade.setBriefDescription ( description );
            System.out.println ("Brief Description Inserted");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Brief Description";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
