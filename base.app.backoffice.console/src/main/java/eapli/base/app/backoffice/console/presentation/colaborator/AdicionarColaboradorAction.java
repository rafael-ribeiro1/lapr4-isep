package eapli.base.app.backoffice.console.presentation.colaborator;

import eapli.framework.actions.Action;

public class AdicionarColaboradorAction implements Action {
    @Override
    public boolean execute(){return new AdicionarColaboratorUI().show();}

}
