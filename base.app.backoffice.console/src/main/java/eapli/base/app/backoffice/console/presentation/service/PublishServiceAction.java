package eapli.base.app.backoffice.console.presentation.service;

import eapli.framework.actions.Action;

public class PublishServiceAction implements Action {
    @Override
    public boolean execute() {
        return new PublishServiceUI ().doShow ();
    }
}
