package eapli.base.app.user.console.presentation.pedido;

import eapli.framework.actions.Action;

public class ConsultTicketsAction implements Action {
    @Override
    public boolean execute() {
        return new ConsultTicketsUI ().show ();
    }
}
