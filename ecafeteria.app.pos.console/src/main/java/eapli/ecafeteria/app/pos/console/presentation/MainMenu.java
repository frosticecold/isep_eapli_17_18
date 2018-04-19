/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    //DELIVERY
    private static final int DELIVER_MEAL_OPTION = 2;
    private static final int DELIVER_MEAL_SUBMENU_OPTION = 1;

    //CHARGE
    private static final int CHARGE_CARD_OPTION = 3;
    private static final int CHARGE_CARD_SUBMENU_OPTION = 1;

    //CLOSE POS
    private static final int CLOSE_POS_OPTION = 4;
    private static final int CLOSE_POS_SUBMENU_OPTION = 1;

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
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCafeteria POS [@" + AuthorizationService.session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        Calendar date = DateTime.now();
        boolean debug = true;

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        //==========================Delivery MENU==================
        if (date.get(Calendar.HOUR_OF_DAY) <= 12 && date.get(Calendar.HOUR_OF_DAY) >= 23 || debug == true) {
            if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
                final Menu deliveryMenu = buildDeliveryMenu();
                mainMenu.add(new SubMenu(DELIVER_MEAL_OPTION, deliveryMenu,
                        new ShowVerticalSubMenuAction(deliveryMenu)));
            }
        } else {
            System.out.println("Can't open delivery menu. Try another time!");
        }

        //==========================Card MENU==================
        if (date.get(Calendar.HOUR_OF_DAY) >= 12 && date.get(Calendar.HOUR_OF_DAY) <= 23 || debug == true) {
            if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
                final Menu cardMenu = buildChargeMenu();
                mainMenu.add(new SubMenu(CHARGE_CARD_OPTION, cardMenu,
                        new ShowVerticalSubMenuAction(cardMenu)));
            }
        } else {
            System.out.println("Can't open charge menu. Try another time!");
        }

        //==========================Close MENU=================
        if (date.get(Calendar.HOUR_OF_DAY) >= 12 && date.get(Calendar.HOUR_OF_DAY) <= 23 || debug == true) {
            if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
                final Menu closeMenu = buildCloseMenu();
                mainMenu.add(new SubMenu(CLOSE_POS_OPTION, closeMenu,
                        new ShowVerticalSubMenuAction(closeMenu)));
            }
        } else {
            System.out.println("Can't close POS. Try another time!");
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildDeliveryMenu() {
        final Menu menu = new Menu("Deliveries >");

        new ViewAvailableMealsUI().doShow();
        menu.add(new MenuItem(DELIVER_MEAL_SUBMENU_OPTION, "Deliver Meal", () -> new RegisterMealDeliveryUI().doShow()));
        menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

        return menu;
    }

    private Menu buildChargeMenu() {
        final Menu menu = new Menu("Chargings >");

        menu.add(new MenuItem(CHARGE_CARD_SUBMENU_OPTION, "Charge Card", () -> new ChargeCardUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

        return menu;
    }

    private Menu buildCloseMenu() {
        final Menu menu = new Menu("Close POS >");

        menu.add(new MenuItem(CLOSE_POS_SUBMENU_OPTION, "Close", () -> new ClosePOSUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

        return menu;
    }
}
