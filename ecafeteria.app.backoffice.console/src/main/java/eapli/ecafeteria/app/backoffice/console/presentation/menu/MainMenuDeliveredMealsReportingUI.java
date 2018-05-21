package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class MainMenuDeliveredMealsReportingUI extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    private static final int SHOWBYDATE_OPTION = 1;
    private static final int SHOWBYMENU_OPTION = 2;
    private static final int SHOWBYTYPE_OPTION = 3;
    private static final int SHOWBYMEAL_OPTION = 4;
    private static final int SHOWBYDISH_OPTION = 5;

    @Override
    protected boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();

    }

    private Menu buildMainMenu() {
        final Menu menu = new Menu();

        menu.add(new MenuItem(SHOWBYDATE_OPTION, "List Delivered Meals by a chosen date",
                () -> new DeliveredMealsByDateUI().show()));
        menu.add(new MenuItem(SHOWBYMENU_OPTION, "List Delivered Meals by a chosen menu",
                () -> new DeliveredMealsByMenuUI().show()));
        menu.add(new MenuItem(SHOWBYTYPE_OPTION, "List Delivered Meals groupped by meal type",
                () -> new DeliveredMealsByTypeUI().show()));
        menu.add(new MenuItem(SHOWBYMEAL_OPTION, "List Delivered Meals by a chosen meal",
                () -> new DeliveredMealsByMealUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
