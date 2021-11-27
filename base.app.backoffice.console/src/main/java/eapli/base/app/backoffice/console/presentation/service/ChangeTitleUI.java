package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.servicemanagement.domain.Title;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeTitleUI extends AbstractUI{
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeTitleUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        System.out.println ("Current Title: "+facade.getTitle ());
        String title= Console.readLine ("Insert Title (Limit "+ Title.TITLE_MAX_LENGTH +" characters)");
        try{
            facade.setTitle ( title );
            System.out.println ("Title Inserted");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Title";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
