package eapli.base.app.backoffice.console.presentation.team;

import eapli.base.app.common.console.presentation.menuselect.ColorPickerWidget;
import eapli.base.common.Cor;
import eapli.base.teammanagement.application.RegistarTipoEquipaController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class RegistarTipoEquipaUI extends AbstractUI {
    private final RegistarTipoEquipaController controller = new RegistarTipoEquipaController();
    private static final Logger LOGGER = LogManager.getLogger(RegistarTipoEquipaUI.class);
    @Override
    protected boolean doShow() {
        final String codigoUnico = Console.readNonEmptyLine("Unique Code", "Unique Code should not be empty");
        final String descricao = Console.readNonEmptyLine("Description", "Description should not be empty");
        Color corEscolhida = ColorPickerWidget.chooseColor("Choose a color");
        if(!Console.readBoolean("Confirm Register (y/n)?")) return  false;
        try{
            controller.registarTipoEquipa(new Cor(corEscolhida.getRed(), corEscolhida.getGreen(), corEscolhida.getBlue()),
                    codigoUnico, descricao);
            System.out.println("Team Type registered with success.");
        }catch(@SuppressWarnings("unused") final ConcurrencyException ex) {
            System.out.println(
                    "WARNING: It is not possible to create the team type because it was changed by another user");
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Team Type";
    }
}
