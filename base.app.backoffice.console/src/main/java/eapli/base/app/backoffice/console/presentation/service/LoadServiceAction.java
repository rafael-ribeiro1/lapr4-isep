package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.actions.Action;

public class LoadServiceAction implements Action {
    @Override
    public boolean execute() {
        return new LoadServiceUI (false).show ();
    }
}
