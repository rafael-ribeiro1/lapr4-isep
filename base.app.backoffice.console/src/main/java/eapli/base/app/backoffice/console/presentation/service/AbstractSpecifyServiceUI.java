package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpecifyServiceUI extends AbstractUI {
    protected final SpecifyServiceUIControllerFacade facade;
    private final ChangeServiceCodeUI serviceCodeUI;
    private final ChangeTitleUI titleUI;
    private final SaveChangesUI saveUI;
    private final ChangeCompleteDescriptionUI completeDescUI;
    private final ChangeBriefDescriptionUI briefDescUI;
    private final ChangeIconUI iconUI;
    private final ChangeCatalogUI catalogUI;
    private final ShowDraftStateUI showDraftStateUI;
    private final ChangeKeywordsUI keywordsUI;
    private final CreateActivityFlowUI activityFlowUI;
    private final ServiceFormsUI formsUI;
    private final FinalizeCreationUI finalizeCreationUI;

    private final boolean isCreateServiceMenu;

    public AbstractSpecifyServiceUI(final boolean isCreateServiceMenu) {
        this.isCreateServiceMenu = isCreateServiceMenu;
        this.facade=new SpecifyServiceUIControllerFacade (isCreateServiceMenu);
        this.serviceCodeUI = new ChangeServiceCodeUI (facade);
        this.titleUI = new ChangeTitleUI (facade);
        this.saveUI = new SaveChangesUI (facade,isCreateServiceMenu);
        this.completeDescUI = new ChangeCompleteDescriptionUI (facade);
        this.briefDescUI = new ChangeBriefDescriptionUI (facade);
        this.iconUI = new ChangeIconUI (facade);
        this.catalogUI = new ChangeCatalogUI (facade);
        this.showDraftStateUI = new ShowDraftStateUI (facade);
        this.keywordsUI = new ChangeKeywordsUI (facade);
        this.activityFlowUI = new CreateActivityFlowUI(facade);
        this.formsUI=new ServiceFormsUI (facade);
        this.finalizeCreationUI=new FinalizeCreationUI ( facade );
    }

    protected boolean showMenu() {
        List<AbstractUI> uis =initMenu ();
        SingleSelectionWidget<AbstractUI> chooser = new SingleSelectionWidget<> ( uis );
        chooser.doSelection ();
        AbstractUI ui = chooser.selectedItem ();
        if(ui==null){
            return true;
        }
        ui.show ();
        return (isCreateServiceMenu && ui.equals ( saveUI ))
                ||ui.equals ( finalizeCreationUI );
    }

    private List<AbstractUI> initMenu(){
        List<AbstractUI> uis = new ArrayList<> (10);
        uis.add ( titleUI );
        if(isCreateServiceMenu){
            uis.add ( serviceCodeUI );
        }
        uis.add ( iconUI );
        uis.add ( briefDescUI );
        uis.add ( completeDescUI );
        uis.add ( keywordsUI );
        uis.add ( catalogUI );
        uis.add ( formsUI );
        uis.add(activityFlowUI);
        uis.add( showDraftStateUI);
        uis.add ( saveUI );
        if(!isCreateServiceMenu){
            uis.add ( finalizeCreationUI );
        }
        return uis;
    }
}
