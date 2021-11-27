package eapli.base.app.user.console.presentation.catalogservicesearch;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogservicesearch.application.SearchCatalogsServicesController;
import eapli.base.catalogservicesearch.domain.CatalogServiceSearchResult;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.List;


public class SearchCatalogsServicesUI extends AbstractUI {

    private final SearchCatalogsServicesController controller = new SearchCatalogsServicesController();

    private final Menu menu = new Menu("Search results");

    @Override
    protected boolean doShow() {
        final String term = Console.readNonEmptyLine("Search term", "Search term cannot be empty");

        CatalogServiceSearchResult result;
        try {
            result = controller.searchCatalogsServices(term);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error accessing database");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        if (!buildResultMenu(result)) {
            System.out.printf("No data found for '%s'.%n", term);
            return false;
        }

        boolean show;
        do {
            show = showResults();
        } while (!show);

        return false;
    }

    private boolean showResults() {
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private boolean buildResultMenu(CatalogServiceSearchResult result) {
        List<Catalog> catalogs = result.catalogs();
        if (catalogs.isEmpty()) return false;
        boolean hasNull = catalogs.remove(null);
        int c = 0;
        menu.addItem(MenuItem.of(c++, "End search", Actions.SUCCESS));
        for (Catalog catalog : catalogs) {
            menu.addSubMenu(c++, buildServicesSubMenu(result, catalog));
        }
        if (hasNull)
            menu.addSubMenu(c++, buildServicesSubMenu(result, null));

        return true;
    }

    private Menu buildServicesSubMenu(CatalogServiceSearchResult result, Catalog catalog) {
        Menu subMenu = catalog == null
                ? new Menu("Services without catalog associated")
                : new Menu(catalog.toString());
        int c = 0;
        subMenu.addItem(MenuItem.of(c++, "Return ", Actions.SUCCESS));
        for (Service s : result.servicesOfCatalog(catalog)) {
            subMenu.addItem(MenuItem.of(c++, s.subject(), () -> {
                System.out.println(s.details()); return false;
            }));
        }
        for (ServiceDraft s : result.serviceDraftsOfCatalog(catalog)) {
            subMenu.addItem(MenuItem.of(c++, s.subject(), () -> {
                System.out.println("Available soon\n"); return false;
            }));
        }
        if (catalog != null)
            subMenu.addItem(MenuItem.of(c++, "Catalog details", () -> {
                System.out.println(catalog.details()); return false;
            }));
        return subMenu;
    }

    @Override
    public String headline() {
        return "Search catalogs/services";
    }
}
