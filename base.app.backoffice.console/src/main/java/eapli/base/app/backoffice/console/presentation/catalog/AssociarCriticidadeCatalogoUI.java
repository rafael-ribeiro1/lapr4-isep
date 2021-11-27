package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.application.AssociarCriticidadeCatalogoController;
import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;


public class AssociarCriticidadeCatalogoUI extends AbstractUI {

    private final AssociarCriticidadeCatalogoController controller = new AssociarCriticidadeCatalogoController();

    @Override
    protected boolean doShow() {

        CatalogDTO selectedCatalog = selectCatalog();
        if(selectedCatalog==null) return false;

        Criticidade selectedCriticidade = selectCriticidade();
        if(selectedCriticidade==null) return false;

        selectedCriticidade = defineNewObjectives(selectedCriticidade);

        if(!Console.readBoolean("Confirm to register Critilitaty level on catalog(y/n)"))return false;

        try{
            controller.associarCriticidadeCatalogo(selectedCatalog,selectedCriticidade);
            System.out.printf("Sucessfully associated Criticality Level %s  to Catalog : %s %n",selectedCriticidade.toString(),selectedCatalog.toString());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error on database while associating Criticality Level to Catalog");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }


    private Criticidade selectCriticidade() {
        System.out.println("---Select the Criticality Level---");
        Iterable<Criticidade> criticidades = controller.getCriticidades();
        if(!criticidades.iterator().hasNext()){
            System.out.println("Need to register first criticality levels first !");
            return null;
        }
        SingleSelectionWidget<Criticidade> criticidadeSelector = new SingleSelectionWidget<>(criticidades,true);
        criticidadeSelector.doSelection();
        return criticidadeSelector.selectedItem();
    }

    private CatalogDTO selectCatalog() {
        System.out.println("----Select the Catalog----");
        Iterable<CatalogDTO> catalogs = controller.getCatalogos();
        if(!catalogs.iterator().hasNext()){
            System.out.println("Need to register Catalogs first !");
            return null;
        }
        SingleSelectionWidget<CatalogDTO> catalogSelector = new SingleSelectionWidget<>(catalogs,true);
        catalogSelector.doSelection();
        return catalogSelector.selectedItem();
    }

    private Criticidade defineNewObjectives(Criticidade selectedCriticidade) {
        if(Console.readBoolean("Do you want to change values of the objectives(y/n)")){
            final int temMaxAprova = Console.readInteger("Write the max value of aprovation:");
            final int temAvgAprova = Console.readInteger("Write the average value of aprovation:");
            final int temMaxReso = Console.readInteger("Write the max value of resolution:");
            final int temAvgReso = Console.readInteger("Write the average value of resolution:");
            selectedCriticidade = controller.defineNewObjectives(selectedCriticidade,temMaxAprova,temAvgAprova,temMaxReso,temAvgReso);
        }
        return selectedCriticidade;
    }


    @Override
    public String headline() {
        return "Associate criticality level to Catalog";
    }
}
