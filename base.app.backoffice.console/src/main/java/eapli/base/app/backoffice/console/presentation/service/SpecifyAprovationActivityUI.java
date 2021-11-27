package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.activity.domain.TipoResponsavel;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class SpecifyAprovationActivityUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public SpecifyAprovationActivityUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        List<String> responsibleType = new ArrayList<>();
        responsibleType.add(TipoResponsavel.RESPONSAVEL_CATALOGO.toString());responsibleType.add(TipoResponsavel.RESPONSAVEL_HIERARQUICO.toString());
        System.out.println("----Approval Activity----");
        System.out.println("Select Type of Responsible");
        SingleSelectionWidget<String> selectionDataType = new SingleSelectionWidget<>(responsibleType,true);
        selectionDataType.doSelection();
        String responsibleCategory = selectionDataType.selectedItem();
        facade.addApprovalActivity(responsibleCategory);
        return false;
    }


    @Override
    public String headline() {
        return null;
    }
}
