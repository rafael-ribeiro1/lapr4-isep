package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * PlaceHolder class for the Selection Widget in ChangeKeywordUI.
 * When the class is called, the ChangeKeywordUI is the class
 * to remove the keyword, as to avoid unnecessary complications with the code.
 */
public class RemoveKeywordUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public RemoveKeywordUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        try{
            removeKeyword ();
            System.out.println ("Keyword Removed");
        }catch (final IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    private void removeKeyword(){
        SingleSelectionWidget<String> removeSelect=new SingleSelectionWidget<> ( facade.keywords () );
        removeSelect.doSelection ();
        String toRemove=removeSelect.selectedItem ();
        if(!facade.containsKeyword ( toRemove ))
            throw new IllegalArgumentException ("Service doesn't contain the keyword "+toRemove);
        facade.withoutKeyword ( toRemove );
    }

    @Override
    public String headline() {
        return "Remove keyword";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
