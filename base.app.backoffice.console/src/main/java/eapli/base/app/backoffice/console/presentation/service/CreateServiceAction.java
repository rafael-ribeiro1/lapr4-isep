package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.actions.Action;

public class CreateServiceAction implements Action {
    @Override
    public boolean execute() {
        return new CreateServiceUI (true).show ();
    }
}
