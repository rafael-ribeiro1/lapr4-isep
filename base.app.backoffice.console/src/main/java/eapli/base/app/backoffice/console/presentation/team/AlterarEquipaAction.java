package eapli.base.app.backoffice.console.presentation.team;

import eapli.framework.actions.Action;

public class AlterarEquipaAction implements Action {
    @Override
    public boolean execute() {
        return new AlterarEquipaUI().show();
    }
}
