package eapli.base.app.backoffice.console.presentation.team;

import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.teammanagement.application.AlterarEquipaController;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class AlterarEquipaUI extends AbstractUI {
    private final AlterarEquipaController controller = new AlterarEquipaController();
    private static final String ADICIONAR_COLABORADOR = "Add Collaborator";
    private static final String REMOVER_COLABORADOR = "Remove Collaborator";
    private static final String MANAGE_TEAM_1 = "Search Team by Unique Code";
    private static final String MANAGE_TEAM_2 = "Search Team by Team Type";
    private static final Logger LOGGER = LogManager.getLogger(AlterarEquipaUI.class);


    @Override
    protected boolean doShow() {

        final SingleSelectionWidget<String> selector = new SingleSelectionWidget<String>(options_search(),
                true);
        selector.doSelection();
        final SingleSelectionWidget<String> actionSelector = new SingleSelectionWidget<>(options(),
                true);
        Equipa equipa;
        if (selector.selectedItem().equals(MANAGE_TEAM_1)){
            final String codigoUnico = Console.readNonEmptyLine("Unique Code:", "");
           equipa = controller.equipaPorCodigoUnico(CodigoUnico.valueOf(codigoUnico));
        }else {
            final Iterable<TipoEquipa> tiposEquipa = this.controller.tiposEquipa();
            final TipoEquipa tipoEquipa;
            Iterable<Equipa> equipas;
            System.out.println("Choose Team Type:");
            final SingleSelectionWidget<TipoEquipa> selectorTipoEquipa = new SingleSelectionWidget<TipoEquipa>(tiposEquipa,
                    true);
            selectorTipoEquipa.doSelection();
            tipoEquipa = selectorTipoEquipa.selectedItem();
            equipas = this.controller.equipas(tipoEquipa);
            System.out.println("Select a team");
            final SingleSelectionWidget<Equipa> equipaSelector = new SingleSelectionWidget<>(equipas);
            equipaSelector.doSelection();
            equipa = equipaSelector.selectedItem();

        }
        actionSelector.doSelection();
        try {
            ColaboradorDTO selected ;
            if (actionSelector.selectedItem().equals(ADICIONAR_COLABORADOR)) {
                Iterable<ColaboradorDTO> l = controller.colaboradoresEquipa(equipa);
                ListWidget<ColaboradorDTO> membrosEquipa = new ListWidget<>("Current Team Members",l);
                membrosEquipa.show();
                if (!l.iterator().hasNext()) System.out.println("The team has no members");

                System.out.println("Select the Collaborator to be added");
                SingleSelectionWidget<ColaboradorDTO> colaboradoresEquipa = new SingleSelectionWidget<>(controller.colaboradoresSemTipoEquipa(equipa.tipoEquipa()), true);
                colaboradoresEquipa.doSelection();
                selected = colaboradoresEquipa.selectedItem();
                if(!Console.readBoolean("Confirm changes (y/n)?")) return  false;
                controller.adicionarColaboradorEquipa(equipa, selected);
            }else{
                System.out.println("Select the Collaborator to be removed");
                SingleSelectionWidget<ColaboradorDTO> colaboradoresEquipa = new SingleSelectionWidget<>(controller.colaboradoresEquipa(equipa), true);
                colaboradoresEquipa.doSelection();
                selected = colaboradoresEquipa.selectedItem();
                if(selected == null){
                    System.out.println("\tThe Team has no Collaborators associated.");
                    return false;
                }
                if(!Console.readBoolean("Confirm changes (y/n)?")) return  false;
                controller.removerColaboradorEquipa(equipa, selected);
            }
            System.out.println("Team changed with success.");
        }catch(@SuppressWarnings("unused") final ConcurrencyException ex) {
            System.out.println(
                    "WARNING: It is not possible to change the team because it was changed by another user");
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private Iterable<String> options(){
        List<String> l = new ArrayList<>();
        l.add(ADICIONAR_COLABORADOR);
        l.add(REMOVER_COLABORADOR);
        return l;
    }

    private Iterable<String> options_search(){
        List<String> l = new ArrayList<>();
        l.add(MANAGE_TEAM_1);
        l.add(MANAGE_TEAM_2);
        return l;
    }

    @Override
    public String headline() {
        return "Change Team Members";
    }
}
