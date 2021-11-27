package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class SaveChangesUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;
    private final boolean isCreateService;

    public SaveChangesUI(SpecifyServiceUIControllerFacade facade, final boolean isCreateService) {
        this.facade = facade;
        this.isCreateService=isCreateService;
    }

    @Override
    protected boolean doShow() {
        try{
            facade.save (isCreateService);
            System.out.println ("Changes saved");
        } catch (IllegalArgumentException | IntegrityViolationException e){
            System.out.println ("Could not save");
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        } catch (ConcurrencyException e){
            System.out.println ("Another transaction to save the same draft instance is already in progress.");
            System.out.println ("Try again in a few seconds.");
            facade.reloadServiceDraft ();
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        if(isCreateService)
            return "Save Changes and Exit";
        return "Save Changes";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
