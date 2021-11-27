package eapli.base.app.user.console.presentation.activity;

import eapli.base.activity.domain.DataLimite;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.activity.application.ReivindicarAtividadesPendentesController;
import eapli.base.activity.domain.RealizacaoManual;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.teammanagement.domain.Equipa;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ReivindicarAtividadesPendentesUI extends AbstractUI {
    private final ReivindicarAtividadesPendentesController controller = new ReivindicarAtividadesPendentesController();
    private static final Logger LOGGER = LogManager.getLogger(ReivindicarAtividadesPendentesUI.class);
    private static final String CRITICIDADE = "Criticality";
    private static final String DATA_LIMITE = "Deadline";
    private static final String PRIORIDADE = "Priority";
    private static final String URGENCIA = "Urgency";

    private static final String ASCENDENTE = "Ascending Order";
    private static final String DESCENDENTE = "Descending Order";
    private static final String NO_MATCH = "None of the pending activities matched the filter criteria\n";
    private  List<Equipa> equipas = controller.equipasUtilizadorAtual();


    @Override
    protected boolean doShow() {
        try {
            return  buildFilterMenu();
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

    private boolean buildFilterMenu() {
        System.out.println("Select a filter criteria");
        SingleSelectionWidget<String> filterSelection = new SingleSelectionWidget<>(Arrays.asList(CRITICIDADE, DATA_LIMITE, URGENCIA),false);
        filterSelection.doSelection();
        String selection = filterSelection.selectedItem();
        if (selection == null) return buildSortMenu();
        switch(selection){
            case CRITICIDADE:
                return buildFilterCriticidadesMenu();
            case DATA_LIMITE:
                return buildFilterDataLimiteMenu();
            case URGENCIA:
                return buildFilterUrgenciaMenu();
        }
        return false;
    }

    private boolean buildFilterUrgenciaMenu() {
        System.out.println("Select a Urgency");
        List<Urgencia> urgencias = controller.findAllUrgencias();
        SingleSelectionWidget<Urgencia> selectUrgencia = new SingleSelectionWidget<>(urgencias, true);
        selectUrgencia.doSelection();
        Urgencia selection = selectUrgencia.selectedItem();
        Iterable<RealizacaoManual> result = controller.filterByUrgencia(selection, equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildFilterCriticidadesMenu(){
        System.out.println("Select a criticality");
        List<Criticidade> criticidades = controller.findAllCriticidades();
        SingleSelectionWidget<Criticidade> selectCriticidade = new SingleSelectionWidget<>(criticidades, true);
        selectCriticidade.doSelection();
        Criticidade selection = selectCriticidade.selectedItem();
        return buildFilterCriticidadeSubMenu(selection);
    }

    private boolean buildFilterCriticidadeSubMenu(Criticidade c) {
        Iterable<RealizacaoManual> result = controller.filterByCriticidade(c, equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildFilterDataLimiteMenu() {
        Calendar d = Console.readCalendar("Insert first date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
        Calendar d1 = Console.readCalendar("Insert second date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
        Iterable<RealizacaoManual> result = controller.filterByIntervaloDataLimite(DataLimite.valueOf(d), DataLimite.valueOf(d1), equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildSortMenu() {
        System.out.println("Select a sort criteria");
        SingleSelectionWidget<String> sortSelection = new SingleSelectionWidget<>(Arrays.asList(CRITICIDADE, DATA_LIMITE, URGENCIA, PRIORIDADE),false);
        sortSelection.doSelection();
        String selection = sortSelection.selectedItem();
        if (selection == null) return buildSimpleMenu();
        switch(selection){
            case CRITICIDADE:
                return buildSortCriticidadesMenu();
            case DATA_LIMITE:
                return buildSortDataLimiteMenu();
            case URGENCIA:
                return buildSortUrgenciaMenu();
            case PRIORIDADE:
                return buildPrioridadeMenu();
        }
        return false;
    }

    private boolean buildSortCriticidadesMenu() {
        boolean asc = selectOrderToFilter();
        Iterable<RealizacaoManual> result = controller.sortByCriticidade(asc, equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildSortDataLimiteMenu() {
        boolean asc = selectOrderToFilter();
        Iterable<RealizacaoManual> result = controller.sortByDataLimite(asc, equipas);
        return buildActivitiesMenu(result);
    }
    private boolean buildSortUrgenciaMenu() {
        boolean asc = selectOrderToFilter();
        Iterable<RealizacaoManual> result = controller.sortByUrgencia(asc, equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildPrioridadeMenu() {
        boolean asc = selectOrderToFilter();
        Iterable<RealizacaoManual> result = controller.sortByPrioridade(asc, equipas);
        return buildActivitiesMenu(result);
    }

    private boolean buildSimpleMenu() {
        return buildActivitiesMenu(controller.atividadesPendentesNaoAtribuidas(equipas));
    }

    private boolean buildActivitiesMenu(Iterable<RealizacaoManual> result) {
        RealizacaoManual selected;
        if(!result.iterator().hasNext()){
           System.out.println(NO_MATCH);
           return false;
       }else{
            System.out.println("Select Pending Activity");
            SingleSelectionWidget<RealizacaoManual> selectionWidget = new SingleSelectionWidget<>(result, true);
            selectionWidget.doSelection();
            selected = selectionWidget.selectedItem();
            if (selected == null){
                return false;
            } else {
                if(!Console.readBoolean("Confirm changes (y/n)?"))
                    return  false;
                controller.reivindicarAtividadePendente(controller.pedidoWithAtividade(selected),controller.colaboradorAutenticado());
                System.out.println("Activity changed with success.");
                return true;
            }
        }

    }

    private boolean selectOrderToFilter(){
        SingleSelectionWidget<String> order = new SingleSelectionWidget<>(Arrays.asList(ASCENDENTE, DESCENDENTE),true);
        System.out.println("----Select the order to filter by----");
        order.doSelection();
        String selected = order.selectedItem();
        return selected.equals(ASCENDENTE);
    }


    @Override
    public String headline() {
        return "Claim Pending Activity";
    }
}
