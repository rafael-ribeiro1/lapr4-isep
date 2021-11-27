package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeServiceCodeUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeServiceCodeUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        System.out.println ("Current Service Code: "+facade.getServiceCode ());
        String serviceCode= Console.readLine ("Insert Service Code");
        try{
            facade.setServiceCode ( serviceCode );
            System.out.println ("Service Code Inserted");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Service Code";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
