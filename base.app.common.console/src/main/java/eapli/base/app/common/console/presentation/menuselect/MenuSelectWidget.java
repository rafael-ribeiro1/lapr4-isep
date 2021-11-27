package eapli.base.app.common.console.presentation.menuselect;

import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;


public abstract class MenuSelectWidget<E> {

    protected final Menu menu = new Menu();
    protected Iterable<E> options;

    protected MenuSelectWidget(Iterable<E> options) {
        if (options == null)
            throw new IllegalArgumentException();

        this.options = options;
    }

    protected abstract void buildMenu();

    protected boolean showOptions() {
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    public abstract void doSelection();

}
