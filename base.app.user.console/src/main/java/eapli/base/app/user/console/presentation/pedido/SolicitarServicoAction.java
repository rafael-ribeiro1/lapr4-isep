package eapli.base.app.user.console.presentation.pedido;

import eapli.framework.actions.Action;

public class SolicitarServicoAction implements Action {

    @Override
    public boolean execute() {
        return new SolicitarServicoUI().show();
    }

}
