package eapli.base.app.backoffice.console.presentation.funcao;

import eapli.framework.actions.Action;

public class AddFuncaoAction implements Action {

    @Override
    public boolean execute() {
        return new AddFuncaoUI().show();
    }

}
