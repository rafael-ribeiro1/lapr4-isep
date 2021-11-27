package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.app.common.console.presentation.icon.IconChooser;
import eapli.base.app.common.console.presentation.menuselect.MultipleSelectionWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.catalogmanagement.application.AddCatalogController;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddCatalogUI extends AbstractUI {

    private final AddCatalogController controller = new AddCatalogController();

    @Override
    protected boolean doShow() {
        final String title = Console.readNonEmptyLine("Title", "Title should not be empty");
        final String briefDesc = Console.readNonEmptyLine("Brief description", "Brief Description should not be empty");
        final String completeDesc = Console.readNonEmptyLine("Complete description", "Complete Description should not be empty");

        final byte[] icon = new IconChooser(false).chooseIcon();

        Iterable<Colaborador> colaboradores = controller.allColaboradores();

        System.out.println("--- Select responsible collaborator ---");
        SingleSelectionWidget<Colaborador> respSelect = new SingleSelectionWidget<>(colaboradores, true);
        respSelect.doSelection();
        Colaborador responsavel = respSelect.selectedItem();

        Iterable<Equipa> equipas = controller.allEquipas();
        Set<Equipa> access = new HashSet<>();

        System.out.println("--- Select teams with access to the catalog ---");
        MultipleSelectionWidget<Equipa> equipaSelect = new MultipleSelectionWidget<>(equipas, access);
        equipaSelect.doSelection();

        if(!Console.readBoolean("Confirm registration (y/n)  ?")) return  false;

        try {
            this.controller.addCatalog(title, briefDesc, completeDesc, icon, responsavel, new ArrayList<>(access));
            System.out.printf("Successfully added catalog '%s'%n", title);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error adding catalog");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Catalog";
    }
}
