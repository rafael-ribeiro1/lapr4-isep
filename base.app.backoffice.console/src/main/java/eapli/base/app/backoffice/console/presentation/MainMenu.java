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
package eapli.base.app.backoffice.console.presentation;


import eapli.base.app.backoffice.console.presentation.catalog.AddCatalogAction;

import eapli.base.app.backoffice.console.presentation.catalog.AssociarCriticidadeCatalogoAction;
import eapli.base.app.backoffice.console.presentation.colaborator.AdicionarColaboradorAction;
import eapli.base.app.backoffice.console.presentation.service.CreateServiceAction;
import eapli.base.app.backoffice.console.presentation.criticidade.DefinirCriticidadeUI;
import eapli.base.app.backoffice.console.presentation.service.LoadServiceAction;
import eapli.base.app.backoffice.console.presentation.service.PublishServiceAction;
import eapli.base.app.backoffice.console.presentation.team.*;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;



    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 3;
    //HR
    private static final int TEAM_OPTION = 4;
    private static final int COLABORATOR_OPTION=5;
    // GSH
    private static final int CATALOG_OPTION = 6;
    private static final int SERVICES_OPTION = 7;
    private static final int CRITICALITY_OPTION=8;

    //SPECIFY SERVICE
    private static final int CREATE_SERVICE_OPTION=1;
    private static final int LOAD_SERVICE_OPTION=2;
    private static final int PUBLISH_SERVICE_OPTION=3;

    //TEAM
    private static final int REGISTER_TEAM_OPTION = 1;
    private static final int MANAGE_TEAM_MEMBERS_OPTION = 2;
    private static final int REGISTER_TEAMTYPE_OPTION = 3;

    // COLABORADOR
    private static final int REGISTER_COLABORADOR = 1;

    // CATALOG
    private static final int REGISTER_CATALOG = 1;
    private static final int ASSOCIATE_CRITICALITY= 2;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

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
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        //Admin/Power user
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }
        //HR
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RESPONSAVEL_RH)) {
            final Menu teamMenu = buildTeamMenu();
            mainMenu.addSubMenu(TEAM_OPTION, teamMenu);
            final Menu colaborator = buildColaboratorMenu();
            mainMenu.addSubMenu(COLABORATOR_OPTION,colaborator);
        }
        //GSH
        if(authz.isAuthenticatedUserAuthorizedTo (BaseRoles.GESTOR_SH)){
            final Menu catalogMenu = buildCatalogMenu();
            mainMenu.addSubMenu(CATALOG_OPTION, catalogMenu);
            final Menu servicesMenu = buildServicesMenu ();
            mainMenu.addSubMenu(SERVICES_OPTION, servicesMenu);
            final Menu criticalityMenu = buildCriticalyMenu();
            mainMenu.addSubMenu(CRITICALITY_OPTION,criticalityMenu);

        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");
        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildServicesMenu(){
        final Menu menu=new Menu ("Services >");
        menu.addItem (CREATE_SERVICE_OPTION, "Create New Service",new CreateServiceAction());
        menu.addItem (LOAD_SERVICE_OPTION,"Continue Service Creation", new LoadServiceAction());
        menu.addItem ( PUBLISH_SERVICE_OPTION, "Publish Service", new PublishServiceAction () );
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }


    private Menu buildTeamMenu(){
        final Menu menu_team = new Menu("Team >");

        //register team
        menu_team.addItem(REGISTER_TEAM_OPTION, "Register Team", new RegistarEquipaAction());
        //manage team members
        menu_team.addItem(MANAGE_TEAM_MEMBERS_OPTION, "Manage Team Members", new AlterarEquipaAction());

        menu_team.addItem(REGISTER_TEAMTYPE_OPTION, "Register Team Type", new RegistarTipoEquipaAction());

        menu_team.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu_team;
    }

    private Menu buildColaboratorMenu(){
        final Menu menuColaborator = new Menu("Colaborator >");

        menuColaborator.addItem(REGISTER_COLABORADOR,"Specify colaborator by terminal", new AdicionarColaboradorAction());
        menuColaborator.addItem(EXIT_OPTION,RETURN_LABEL,Actions.SUCCESS);
        return menuColaborator;
    }

    private Menu buildCriticalyMenu() {
        final Menu menuCriticalityMenu = new Menu("Criticality>");

        final Menu definirCriticality = new Menu("Define new Criticality Level:");
        menuCriticalityMenu.addItem(1,"New Criticality Level",new DefinirCriticidadeUI()::show);
        definirCriticality.addItem(EXIT_OPTION,RETURN_LABEL,Actions.SUCCESS);

        menuCriticalityMenu.addItem(EXIT_OPTION,RETURN_LABEL,Actions.SUCCESS);
        return menuCriticalityMenu;
    }


    private Menu buildCatalogMenu() {
        final Menu menu = new Menu("Catalog >");
        menu.addItem(REGISTER_CATALOG, "Register catalog", new AddCatalogAction());
        menu.addItem(ASSOCIATE_CRITICALITY,"Associate Criticality Level to Catalog", new AssociarCriticidadeCatalogoAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}