package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.hibernate.LazyInitializationException;

public class FinalizeCreationUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public FinalizeCreationUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        boolean create=Console.readBoolean ( "Are you sure you want to finalize the creation of the service?(y/n)?" );
        if(create)
            try{
                facade.finalizeServiceCreation ();
                System.out.println ("Service published");
            }catch (IllegalArgumentException | IntegrityViolationException | ConcurrencyException e) {
                System.out.println ( e.getMessage () );
            }catch (LazyInitializationException e){
                facade.keywords ();//Explicitly loading the keywords in case they have not already been loaded
                facade.finalizeServiceCreation ();
            }
        return true;
    }

    @Override
    public String headline() {
        return "Confirm creation";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
