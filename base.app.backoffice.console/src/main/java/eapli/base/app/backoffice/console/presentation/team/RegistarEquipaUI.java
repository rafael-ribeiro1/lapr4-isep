package eapli.base.app.backoffice.console.presentation.team;

import eapli.base.app.common.console.presentation.menuselect.MultipleSelectionWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;

import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.teammanagement.application.RegistarEquipaController;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RegistarEquipaUI extends AbstractUI {
    private final RegistarEquipaController controller = new RegistarEquipaController();

    @Override
    protected boolean doShow() {
        final String codigoUnico = Console.readNonEmptyLine("Unique Code:", "Unique Code should not be empty");
        final String acronimo = Console.readNonEmptyLine("Acronym:", "Acronym should not be empty");
        final String designacao = Console.readNonEmptyLine("Designation:", "Designation should not be empty");
        Set<ColaboradorDTO> colaboradoresEquipa = new HashSet<>();
        Set<ColaboradorDTO> responsaveisEquipa = new HashSet<>();
        TipoEquipa tipoEquipa;

        Iterable<TipoEquipa> tiposEquipa = controller.tiposEquipa();
        System.out.println("--- Select Team Type ---");
        SingleSelectionWidget<TipoEquipa> tipoEquipaSelect = new SingleSelectionWidget<>(tiposEquipa);
        tipoEquipaSelect.doSelection();
        tipoEquipa = tipoEquipaSelect.selectedItem();

        Iterable<ColaboradorDTO> colaboradores = controller.colaboradores(tipoEquipa);
        System.out.println("--- Select Responsible Collaborators ---");
        final MultipleSelectionWidget<ColaboradorDTO> selectorResponsavel = new MultipleSelectionWidget<>(colaboradores,
                responsaveisEquipa);
        selectorResponsavel.doSelection();

        Set<ColaboradorDTO> tmp = new HashSet<>((Collection<? extends ColaboradorDTO>) colaboradores);
        tmp.removeAll(responsaveisEquipa);
        System.out.println("--- Select Collaborators (Team Members) ---");
        final MultipleSelectionWidget<ColaboradorDTO> selectorColaborador = new MultipleSelectionWidget<>(tmp,
                colaboradoresEquipa);
        selectorColaborador.doSelection();
        if(!Console.readBoolean("Confirm Register (y/n)?")) return  false;
        try{
            this.controller.registarEquipa(tipoEquipa, acronimo, codigoUnico, designacao, colaboradoresEquipa, responsaveisEquipa);
            System.out.println("Team registered with success.");
        }catch (@SuppressWarnings("unused") final IntegrityViolationException  | ConcurrencyException e){
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Team";
    }
}
