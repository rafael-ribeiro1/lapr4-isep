package eapli.base.app.backoffice.console.presentation.criticidade;

import eapli.base.app.backoffice.console.presentation.catalog.AddCatalogUI;

import eapli.framework.actions.Action;

public class DefinirCriticidadeAction implements Action {

    @Override
    public boolean execute() {
        return new DefinirCriticidadeUI().show();
    }
}
