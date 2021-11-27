package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.servicemanagement.domain.Title;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.function.Function;

public class ChangeFormNameUI extends AbstractUI {
    private final Function<String,Void> nameFunction;

    public ChangeFormNameUI(Function<String,Void> nameFunction) {
        this.nameFunction=nameFunction;
    }

    @Override
    protected boolean doShow() {
        try{
            String name= Console.readLine ("Insert Form Name");
            nameFunction.apply ( name );
            System.out.println ("Name added");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Change Form Name";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
