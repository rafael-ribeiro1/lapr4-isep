package eapli.base.app.user.console.presentation.activity;

import eapli.base.activity.application.VerAtividadesPendentesController;
import eapli.base.activity.domain.Atividade;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.*;

public class VerAtivididadesPendentesUI extends AbstractUI {

    private static final String CRITICIDADE = "Criticality";
    private static final String DATA_LIMITE = "Deadline";
    private static final String URGENCIA = "Urgencia";

    private static final String ASCENDENTE = "Most Recent";
    private static final String DESCENDENTE = "Least Recent";

    private final VerAtividadesPendentesController controller = new VerAtividadesPendentesController();


    @Override
    protected boolean doShow() {
        renderMenu(buildMenu());
        return false;
    }

    private Menu buildMenu() {
       Menu mainMenu = new Menu("Choose filter to search");
        System.out.println("Choose filter to search");
       int c = 0;
        mainMenu.addItem(MenuItem.of(c++,"Exit", Actions.SUCCESS));
        mainMenu.addItem(MenuItem.of(c++,CRITICIDADE,()->{
           renderMenu(buildCriticidadesMenu());
           return false; }));
        mainMenu.addItem(MenuItem.of(c++,DATA_LIMITE,()->{
            renderMenu(buildDataLimiteSubMenu());
            return false; }));
        mainMenu.addItem(MenuItem.of(c++, URGENCIA,()->{
            renderMenu(buildUrgenciaMenu());
            return false; }));
        return mainMenu;
    }

    private Menu buildCriticidadesMenu(){
        Menu menu = new Menu ("Choose the filter of criticidade");
        System.out.println("Select the criticality");
        int c = 0;
        menu.addItem(MenuItem.of(c++,"Exit", Actions.SUCCESS));
        Iterable<Criticidade> criticidades = controller.listAllCriticidades();
        for(Criticidade crit : criticidades){
            menu.addItem(MenuItem.of(c++,crit.etiqueta(),()->{
                renderMenu(buildCriticidadeSubMenu(crit));
                return false; }));
        }
        return menu;
    }

    private Menu buildCriticidadeSubMenu(Criticidade c){
        Menu menu = new Menu (String.format("Obtained result for criticidade %s",c.etiqueta()));
        boolean order = selectOrderToFilter();
        List<Atividade> obtainedAtividades = controller.searchByCriticidadePendingActivities(c,order);
        menu.addItem(MenuItem.of(0,"Exit", Actions.SUCCESS));
        return buildActivitiesMenu(menu,obtainedAtividades);
    }

    private Menu buildDataLimiteSubMenu(){
        Menu menu = new Menu("Choose Limit Date");
        try{
            Calendar limiteDate = Console.readCalendar("Limit date for activity (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
            boolean ascending = this.selectOrderToFilter();
            List<Atividade> obtainedAtividades = controller.searchByLimitDateActivities(limiteDate,ascending);
            menu.addItem(MenuItem.of(0,"Exit", Actions.SUCCESS));
            return buildActivitiesMenu(menu,obtainedAtividades);
        }catch (IllegalArgumentException e){
            System.out.println("Write the correct format for date !");
           return buildDataLimiteSubMenu();
        }
    }

    private Menu buildUrgenciaSubMenu(Urgencia urgencia){
        Menu menu = new Menu("Obtained result for Urgencia");
        boolean ascending = this.selectOrderToFilter();
        List<Atividade> obtainedAtividades = controller.searchByUrgenciasPendingActivities(urgencia,ascending);
        menu.addItem(MenuItem.of(0,"Exit", Actions.SUCCESS));
        return buildActivitiesMenu(menu,obtainedAtividades);
    }



    private Menu buildUrgenciaMenu(){
        Menu menu = new Menu("Choose the urgency");
        System.out.println("Select the urgency");
        int c = 0;
        menu.addItem(MenuItem.of(c++,"Exit",Actions.SUCCESS));
        List<Urgencia> urgenciasList = Arrays.asList(Urgencia.values());
        for(Urgencia urgencia: urgenciasList){
            menu.addItem(MenuItem.of(c++,urgencia.toString(),()->{
                renderMenu(buildUrgenciaSubMenu(urgencia));
                return false;
            }));
        }
        return menu;
    }


    private Menu buildActivitiesMenu(Menu menu,Iterable<Atividade> atividadesPendentes){
        int c = 1;
        if(!atividadesPendentes.iterator().hasNext()){
            System.out.printf("-----------------%nNo Pending Activities!%n-----------%n");
            return menu;
        }
        System.out.println("----Obtained Results----");
        for(Atividade a : atividadesPendentes){
            menu.addItem(MenuItem.of(c,a.toString(),()->{
                System.out.printf("----Activity information----%n%s%n%n-----------%n",controller.seeDetailedInfo(a)); return false;
            }));
            c++;
        }
        return menu;
    }

    private boolean showResults(Menu menuToRender) {
        final MenuRenderer renderer = new VerticalMenuRenderer(menuToRender, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private boolean renderMenu (Menu menuToRender){
        boolean show;
        do{
            show=showResults(menuToRender);
        }while (!show);
        return false;
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
        return "See Pending Activities to realize";
    }
}
