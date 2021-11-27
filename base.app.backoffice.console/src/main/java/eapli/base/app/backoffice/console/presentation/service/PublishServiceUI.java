package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.servicemanagement.application.PublishServiceController;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.dto.ServiceDTO;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Collection;
import java.util.List;

public class PublishServiceUI extends AbstractUI {
    private static final PublishServiceController controller=new PublishServiceController ();
    @Override
    protected boolean doShow() {
        List<ServiceDTO> services=controller.getAllUnpublishedServices ();
        SingleSelectionWidget<ServiceDTO> widget=new SingleSelectionWidget<> ( services );
        System.out.println ("Select service to publish");
        widget.doSelection ();
        ServiceDTO dto= widget.selectedItem ();
        controller.publishService ( dto.serviceCode );
        System.out.println ("Service "+dto.serviceCode+" published");
        return true;
    }

    @Override
    public String headline() {
        return "Publish Service";
    }
}
