package eapli.base.app.common.console.presentation.menuselect;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.MenuItem;

import java.util.Collection;

public class MultipleSelectionWidget<E> extends MenuSelectWidget<E> {

    private Collection<E> selection;

    public MultipleSelectionWidget(Iterable<E> options, Collection<E> selection) {
        super(options);

        if (selection == null)
            throw new IllegalArgumentException();

        this.selection = selection;

        buildMenu();
    }

    @Override
    protected void buildMenu() {
        int counter = 0;
        menu.addItem(MenuItem.of(counter++, "End selection", Actions.SUCCESS));
        for (final E e : options) {
            menu.addItem(MenuItem.of(counter++, e.toString(), () -> {selection.add(e); return false;}));
        }
    }

    @Override
    public void doSelection() {
        boolean show;
        do {
            show = showOptions();
        } while (!show);
    }
}
