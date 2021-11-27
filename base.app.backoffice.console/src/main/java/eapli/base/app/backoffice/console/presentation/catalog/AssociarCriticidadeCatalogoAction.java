package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.framework.actions.Action;

public class AssociarCriticidadeCatalogoAction implements Action {


    @Override
    public boolean execute() {
        return new AssociarCriticidadeCatalogoUI().show();
    }
}
