package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddKeywordUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public AddKeywordUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        String keyword= Console.readLine ("Insert Keyword");
        try{
            addKeyword (keyword);
        }catch (final IllegalArgumentException | ConcurrencyException e){
            System.out.println ("Failed to insert keyword");
            System.out.println (e.getMessage ());
        }
        return true;
    }

    private void addKeyword(final String keyword){
        if (facade.containsKeyword ( keyword ))
            throw new IllegalArgumentException ( "Service already contains the keyword " + keyword + "" );
        facade.withKeyword ( keyword );
    }

    @Override
    public String headline() {
        return "Add keyword";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
