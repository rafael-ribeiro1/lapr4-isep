package eapli.base.app.common.console.presentation.menuselect;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.MenuItem;

public class SingleSelectionWidget<E> extends MenuSelectWidget<E> {

    private E selection;

    private boolean mandatorySelection;

    public SingleSelectionWidget(Iterable<E> options) {
        super(options);
        selection = null;
        mandatorySelection=false;
        buildMenu();
    }

    public SingleSelectionWidget(Iterable<E> options,boolean mandatory) {
        super(options);
        selection = null;
        mandatorySelection=mandatory;
        buildMenu();
    }

    @Override
    protected void buildMenu() {
        int counter = 0;
        if(!mandatorySelection)menu.addItem(MenuItem.of(counter++, "No selection", Actions.SUCCESS));
        for (final E e : options) {
            menu.addItem(MenuItem.of(counter++, e.toString(), () -> {selection = e; return true;}));
        }
    }

    @Override
    public void doSelection() {
        int size = 0;
        for (E e : options) size++;
        if (size > 0) showOptions();
    }

    public E selectedItem() {
        E selected=this.selection;
        this.selection=null;
        return selected;
    }
}
