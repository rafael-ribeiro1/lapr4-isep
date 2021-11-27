package eapli.base.app.user.console.presentation.activity;


import eapli.framework.actions.Action;

public class ReivindicarAtividadesPendentesAction implements Action {
    @Override
    public boolean execute() {
        return new ReivindicarAtividadesPendentesUI().show();
    }
}
