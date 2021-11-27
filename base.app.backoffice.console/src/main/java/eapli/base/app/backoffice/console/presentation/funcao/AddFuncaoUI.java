package eapli.base.app.backoffice.console.presentation.funcao;

import eapli.base.colaboratormanagement.application.AddFuncaoController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddFuncaoUI extends AbstractUI {

    private final AddFuncaoController controller = new AddFuncaoController();


    @Override
    protected boolean doShow() {
        final String name = Console.readLine("Designation");

        try {
            this.controller.addFuncao(name);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error adding function");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add function";
    }
}
