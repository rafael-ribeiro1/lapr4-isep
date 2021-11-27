package eapli.base.app.backoffice.console.presentation.colaborator;

import eapli.base.app.common.console.presentation.menuselect.MultipleSelectionWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.colaboratormanagement.application.AdicionarColaboradorController;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.time.util.Calendars;

import javax.persistence.RollbackException;
import java.util.Calendar;
import java.util.HashSet;

import java.util.Set;

/**
 * UI for adding a colaborator to the application. Created by Eduardo Couto (1190549).
 */
public class AdicionarColaboratorUI extends AbstractUI {

   private final AdicionarColaboradorController controller = new AdicionarColaboradorController();

    @Override
    protected boolean doShow() {

        final String username = Console.readNonEmptyLine("Name of the username:","Write a non empty username");
        final String numMecano = Console.readNonEmptyLine("Mecanographic Number:","Write a non empty mecanographic number");
        final String institucionalEmail = Console.readNonEmptyLine("Institucional Email:","Write a non empty email");
        final String firstName = Console.readNonEmptyLine("First name:","Write a non empty first name");
        final String lastName = Console.readNonEmptyLine("Last name:","Write a non empty last name");
        final String completeName = Console.readNonEmptyLine("Full name:","Write a non empty full name");
        final Calendar birthDate = Console.readCalendar("Birthday date(dd-MM-yyyy):");
        final String district = Console.readNonEmptyLine("Current District:","Write a non empty district");
        final String county = Console.readNonEmptyLine("Current County:","Write a non empty county");
        final String contactNumber = Console.readNonEmptyLine("Contact Number","Write a non empty contact");

        Funcao function = doSelectionFunction();
        ColaboradorDTO responsavel = doSelectColaborator();
        Set<Equipa> selectedEquipas = doSelectEquips();

        if(!Console.readBoolean("Confirm Register (y/n)  ?")) return  false;

        try{
           controller.registerColaborator(username,institucionalEmail,numMecano,firstName,lastName,completeName,birthDate,district,county,contactNumber,function,responsavel,selectedEquipas);
            System.out.println("Colaborator registered with sucess");
        }catch (final IntegrityViolationException | ConcurrencyException  |RollbackException e){
            System.out.println("Error adding colaborator");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }

        return false;
    }

    private Funcao doSelectionFunction(){
        System.out.println("----Select Function----");
        Iterable<Funcao> funcoes = controller.listFunctions();
        SingleSelectionWidget<Funcao> funcaoSelector = new SingleSelectionWidget<>(funcoes);
        funcaoSelector.doSelection();
        return funcaoSelector.selectedItem();
    }

    private ColaboradorDTO doSelectColaborator(){
        System.out.println("----Select hierarchical responsible----");
        Iterable<ColaboradorDTO> colaboradores = controller.listColaboradores();
        SingleSelectionWidget<ColaboradorDTO> selecaoResponsavel = new SingleSelectionWidget<>(colaboradores);
        selecaoResponsavel.doSelection();
        return selecaoResponsavel.selectedItem();
    }

    private Set<Equipa> doSelectEquips(){
        System.out.println("----Select Teams ----");
        Set<Equipa> selectedEquipas = new HashSet<>();
        Iterable<Equipa> equipa = controller.listEquipas();
        MultipleSelectionWidget<Equipa> equipaSelect = new MultipleSelectionWidget<>(equipa,selectedEquipas);
        boolean showError;
        do {
            selectedEquipas.clear();
            equipaSelect.doSelection();
            showError=controller.validateEquipasColaborador(selectedEquipas);
            if(!showError) System.out.println("Cant select teams with same type");
        } while(!showError);
        return selectedEquipas;
    }



    @Override
    public String headline() {
        return "Specify new Collaborator";
    }
}
