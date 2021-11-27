package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.app.common.console.presentation.menuselect.MultipleSelectionWidget;
import eapli.base.app.common.console.presentation.menuselect.SingleSelectionWidget;
import eapli.base.app.common.console.presentation.script.ScriptChooser;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.servicemanagement.domain.form.DataType;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecifyRealizationActivityUI  extends AbstractUI  {

    private final SpecifyServiceUIControllerFacade facade;

    private static final String ATIVIDADE_REALIZACAOA = "Automatic Activity";
    private static final String ATIVIDADE_REALIZACAOM = "Manual Activity";
    private static final String EQUIPA = "Team";
    private static final String COLABORADOR = "Collaborator";

    public SpecifyRealizationActivityUI(SpecifyServiceUIControllerFacade facade) {
        this.facade = facade;
    }

    @Override
    protected boolean doShow() {
    try {
        String typeActivity = selectTypeActivity();

        if (typeActivity.equals(ATIVIDADE_REALIZACAOM)) {
            registarManualActivity();
        } else {
            System.out.println("----Automatic Realization Activity----");
            ScriptChooser s = new ScriptChooser();
            byte[] content;
            content = s.chooseScript();
            facade.addAutomaticRealizationActivity(content);
        }
    }catch(IllegalArgumentException e){
        System.out.println("Error: " + e.getMessage());
    }
        return false;
    }


    private String selectTypeActivity(){
        List<String> options = new ArrayList<>();
        options.add(ATIVIDADE_REALIZACAOA);options.add(ATIVIDADE_REALIZACAOM);
        System.out.println("----Realization Activity----");
        SingleSelectionWidget<String> selectionTypeActivity = new SingleSelectionWidget<>(options, true);
        selectionTypeActivity.doSelection();
        return  selectionTypeActivity.selectedItem();
    }

    private void registarManualActivity(){
        System.out.println("----Manual Realization Activity----");
        String name = Console.readNonEmptyLine("Write the name of the form", "Write a non empty name");
        int numberInteractions = Console.readInteger("Write the number of attributes the form has ");
        List<AttributeDTO> listaAttributes = specifyForm(numberInteractions);
        //Decidir Entre Collaborator e Team
        ScriptChooser s = new ScriptChooser();
        byte[] validateResponseScript;
        validateResponseScript = s.chooseScript();
        String selection = decideRole();
        if(selection.equals(EQUIPA)){
            Set<Equipa> teams = decideTeams();
            facade.addManualRealizationActivity(name, listaAttributes, teams,validateResponseScript);
            return;
        }
        ColaboradorDTO selectedColaborator = decideColaborator();
        facade.addManualRealizationActivity(name,listaAttributes,selectedColaborator,validateResponseScript);

    }



    private List<AttributeDTO> specifyForm(int numberInteractions){
        List<AttributeDTO> listaAttributes = new ArrayList<>();
        for (int i = 0; i < numberInteractions; i++) {
            System.out.printf("-------------------%nAttribute number %d%n-------------------%n", i + 1);
            String nameAtribute = Console.readNonEmptyLine("Write the name of the attribute", "Write a non empty name for the attribute");
            String description = Console.readNonEmptyLine("Write the description of the attribute", "Write a non empty description  for the attribute");
            String label = Console.readNonEmptyLine("Write the label of the attribute", "Write a non empty label  for the attribute");
            List<String> dataTypes = new ArrayList<>();
            dataTypes.add(DataType.DATE.toString());dataTypes.add(DataType.BOOLEAN.toString());dataTypes.add(DataType.NUMBER.toString()); dataTypes.add(DataType.TEXT.toString());
            System.out.println("Select the type of Attribute");
            SingleSelectionWidget<String> selectionDataType = new SingleSelectionWidget<>(dataTypes, true);
            selectionDataType.doSelection();
            String dataType = selectionDataType.selectedItem();
            String regex = Console.readNonEmptyLine("Write the regex of the attribute", "Write a non empty regex");
            AttributeDTO a = new AttributeDTO(nameAtribute, description, label, dataType, regex);
            listaAttributes.add(a);
        }
        return listaAttributes;
    }

    private String decideRole(){
        List<String> options = new ArrayList<>();
        options.add(EQUIPA);options.add(COLABORADOR);
        System.out.println("Select Type of Responsible");
        SingleSelectionWidget<String> selectionTypeColaborator = new SingleSelectionWidget<>(options, true);
        selectionTypeColaborator.doSelection();
        return selectionTypeColaborator.selectedItem();
    }



    private Set<Equipa> decideTeams(){
        Set<Equipa> teams = new HashSet<>();
        Iterable<Equipa> listaEquipas = facade.availableTeams();
        MultipleSelectionWidget<Equipa> widgetTeam = new MultipleSelectionWidget<>(listaEquipas, teams);
        widgetTeam.doSelection();
        return teams;
    }

    private ColaboradorDTO decideColaborator() {
        Iterable<ColaboradorDTO> colaboradorDTOS = facade.availableColaboradores();
        System.out.println("Select the Responsible Colaborator");
        SingleSelectionWidget<ColaboradorDTO> selectColaborator = new SingleSelectionWidget<>(colaboradorDTOS,true);
        selectColaborator.doSelection();
        return selectColaborator.selectedItem();
    }


    @Override
    public String headline() {
        return null;
    }
}
