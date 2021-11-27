package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangeCatalogUI extends AbstractUI {
    private final SpecifyServiceUIControllerFacade facade;

    public ChangeCatalogUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
        try {
            System.out.println ( "Current Brief Description: " + facade.getCatalog () );
            Iterable<CatalogDTO> c = facade.allCatalogs ();
            SingleSelectionWidget<CatalogDTO> catalogSelect = new SingleSelectionWidget<> ( c );
            catalogSelect.doSelection ();
            CatalogDTO catalogDTO = catalogSelect.selectedItem ();
            Criticidade criticality = catalogDTO.criticidade;
            boolean retainCriticality = Console.readBoolean ( "Do you wish to retain the same criticidade of the catalog(y/n) ?" );
            if (!retainCriticality) {
                Iterable<Criticidade> criticidadess = facade.allCriticalities ();
                SingleSelectionWidget<Criticidade> criticalitySelector = new SingleSelectionWidget<> ( criticidadess );
                criticalitySelector.doSelection ();
                criticality = criticalitySelector.selectedItem ();
            }
            facade.setCriticality ( criticality );
            facade.setCatalog ( catalogDTO );
        }catch(IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    @Override
    public String headline() {
        return "Catalog";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
