package eapli.base.app.user.console.presentation.catalogservicesearch;

import eapli.framework.actions.Action;

public class SearchCatalogsServicesAction implements Action {

    @Override
    public boolean execute() {
        return new SearchCatalogsServicesUI().show();
    }

}
