package eapli.base.app.backoffice.console.presentation.service;

public class CreateServiceUI extends AbstractSpecifyServiceUI {

    public CreateServiceUI(final boolean isCreateServiceMenu) {
        super ( isCreateServiceMenu );
    }

    @Override
    protected boolean doShow() {
        boolean exit;
        do{
            exit=showMenu ();
        }while (!exit);
        return true;
    }

    @Override
    public String headline() {
        return "Create Service";
    }
}