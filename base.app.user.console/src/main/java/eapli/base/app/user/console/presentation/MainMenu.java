/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.user.console.presentation;

import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.app.user.console.presentation.activity.RealizarAtividadeAction;
import eapli.base.app.user.console.presentation.activity.ReivindicarAtividadesPendentesAction;
import eapli.base.app.user.console.presentation.activity.VerAtividadesPendentesAction;
import eapli.base.app.user.console.presentation.catalogservicesearch.SearchCatalogsServicesAction;
import eapli.base.app.user.console.presentation.pedido.ConsultTicketsAction;
import eapli.base.app.user.console.presentation.pedido.SolicitarServicoAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int CATSERV_OPTION = 2;
    private static final int PEDIDO_OPTION = 3;
    private static final int ACTIVITY_OPTION = 4;

    // CATALOGS/SERVICES MENU
    private static final int SEARCH_OPTION = 1;

    // PEDIDO MENU
    private static final int REQUEST_SERVICE = 1;
    private static final int CONSULT_TICKETS = 2;

    //ACTIVITY
    private static final int CLAIM_PENDING_ACTIVITY = 1;
    private static final int CHECK_PENDING_ACTIVITY = 2;
    private static final int REALIZE_PENDING_ACTIVITY = 3;

    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer =
                new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.COLABORADOR)) {
            final Menu searchMenu = buildCatalogServiceMenu();
            mainMenu.addSubMenu(CATSERV_OPTION, searchMenu);
            final Menu pedidoMenu = buildPedidoMenu();
            mainMenu.addSubMenu(PEDIDO_OPTION, pedidoMenu);
            final Menu activityMenu = buildActivityMenu();
            mainMenu.addSubMenu(ACTIVITY_OPTION,activityMenu);
        }

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildCatalogServiceMenu() {
        final Menu menu = new Menu("Catalogs/Services >");
        menu.addItem(SEARCH_OPTION, "Search catalogs/services", new SearchCatalogsServicesAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }

    private Menu buildPedidoMenu() {
        final Menu menu = new Menu("Requests >");
        menu.addItem(REQUEST_SERVICE, "Request service", new SolicitarServicoAction());
        menu.addItem(CONSULT_TICKETS,"Consult tickets",new ConsultTicketsAction ());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }

    private Menu buildActivityMenu(){
        final Menu menu = new Menu("Activity >");
        menu.addItem(CLAIM_PENDING_ACTIVITY, "Claim Pending Activity", new ReivindicarAtividadesPendentesAction());
        menu.addItem(CHECK_PENDING_ACTIVITY,"Check Pending Activities", new VerAtividadesPendentesAction());
        menu.addItem(REALIZE_PENDING_ACTIVITY, "Realize Pending Activity", new RealizarAtividadeAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }
}
