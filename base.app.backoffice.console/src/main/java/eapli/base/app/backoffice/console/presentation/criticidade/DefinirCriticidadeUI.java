package eapli.base.app.backoffice.console.presentation.criticidade;

import eapli.base.app.common.console.presentation.menuselect.ColorPickerWidget;
import eapli.base.common.Cor;
import eapli.base.criticidademanagement.application.DefinirCriticidadeController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.swing.*;
import java.awt.*;

public class DefinirCriticidadeUI extends AbstractUI {

    private final DefinirCriticidadeController controller = new DefinirCriticidadeController();

    @Override
    protected boolean doShow() {
        final String etiqueta = Console.readNonEmptyLine("Write the etiqueta","Write a non empty etiqueta");
        final int valorCriticidade = Console.readInteger("Write the value representative of criticality");
        Color  corEscolhida = ColorPickerWidget.chooseColor("Select a color");
        final int temMaxAprova = Console.readInteger("Write the max minutes for aprovation");
        final int temAvgAprova = Console.readInteger("Write the average minutes for aprovation");
        final int temMaxReso = Console.readInteger("Write the max minutes for resolution");
        final int temAvgReso = Console.readInteger("Write the average minutes for resolution");
        if(!Console.readBoolean("Confirm the registration for criticidade (y/n)")) return false;
        try{
            controller.registarCriticidade(etiqueta,valorCriticidade,corEscolhida,temMaxAprova,temAvgAprova,temMaxReso,temAvgReso);
            System.out.printf("Successfully added Criticality Level %s %n",etiqueta);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error adding Criticality level to repository");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Specify Criticality level";
    }
}
