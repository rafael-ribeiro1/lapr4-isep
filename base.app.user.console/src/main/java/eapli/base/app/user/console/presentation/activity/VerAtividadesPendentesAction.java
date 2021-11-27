package eapli.base.app.user.console.presentation.activity;

import eapli.framework.actions.Action;

public class VerAtividadesPendentesAction implements Action {

    @Override
    public boolean execute() {
        return new VerAtivididadesPendentesUI().show();
    }
}
