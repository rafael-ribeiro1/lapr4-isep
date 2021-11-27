package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.servicemanagement.dto.ServiceDraftDTO;

public class LoadServiceUI extends AbstractSpecifyServiceUI {

    public LoadServiceUI(final boolean isCreateServiceMenu) {
        super ( isCreateServiceMenu );
    }

    @Override
    protected boolean doShow() {
        boolean exit=selectServiceDraftToLoad ();
        while (!exit){
            exit=showMenu ();
        }
        return true;
    }

    @Override
    public String headline() {
        return "Load Service";
    }

    /**
     *
     * @return True if it's to exit the menu. False otherwise.
     */
    private boolean selectServiceDraftToLoad(){
        Iterable<ServiceDraftDTO> drafts = facade.allServices ();
        SingleSelectionWidget<ServiceDraftDTO> select=new SingleSelectionWidget<> ( drafts );
        select.doSelection ();
        ServiceDraftDTO dto=select.selectedItem ();
        if(dto==null)
            return true;
        facade.setDTO ( dto );
        facade.loadService ( dto );
        return false;
    }

}