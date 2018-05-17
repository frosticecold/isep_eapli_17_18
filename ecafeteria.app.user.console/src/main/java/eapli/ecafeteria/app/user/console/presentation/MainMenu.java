/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.app.user.console.presentation.bookings.BookingMealUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.CancelBookingUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.CheckBookingsByUserUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.ConsultMealRatingUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.CreateAlergenProfileAction;
import eapli.ecafeteria.app.user.console.presentation.bookings.CreateAlergenProfileUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.ListMenuUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.RatingMealUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.ShowTransactionsUI;
import eapli.ecafeteria.app.user.console.presentation.bookings.ViewRatingsUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int ALERGEN_PROFILE_OPTION = 5;

    // BOOKINGS MENU
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int VIEW_RATINGS_OPTION = 3;
    private static final int RATE_MEAL_OPTION = 4;
    private static final int CANCEL_BOOKING = 5;
    private static final int LIST_MENU = 6;
    private static final int CHECK_BOOKINGS = 7;
    private static final int CONSULT_MEAL_RATING = 8;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

    //AlergenProfile
    private static final int CREATE_ALERGEN_PROFILE_OPTION = 1;
    private static final int ADD_ALERGEN_TO_PROFILE_OPTION = 2;
    private static final int REMOVE_ALERGEN_TO_PROFILE_OPTION = 3;

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
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.add(new SubMenu(BOOKINGS_OPTION, bookingsMenu, new ShowVerticalSubMenuAction(bookingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu accountMenu = buildAccountMenu();
        mainMenu.add(new SubMenu(ACCOUNT_OPTION, accountMenu, new ShowVerticalSubMenuAction(accountMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu settingsMenu = buildAdminSettingsMenu();
        mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));

        mainMenu.add(VerticalSeparator.separator());
        final Menu AlergenProfileMenu = buildAlergenProfileMenu();

        mainMenu.add(new SubMenu(ALERGEN_PROFILE_OPTION, AlergenProfileMenu, new ShowVerticalSubMenuAction(AlergenProfileMenu)));

        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAccountMenu() {
        final Menu menu = new Menu("Account");
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, "List movements", () -> new ShowTransactionsUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.add(new MenuItem(LIST_MENUS_OPTION, "List menus", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a meal", () -> new BookingMealUI().show()));
        menu.add(new MenuItem(VIEW_RATINGS_OPTION, "View Ratings", () -> new ViewRatingsUI().show()));
        menu.add(new MenuItem(RATE_MEAL_OPTION, "Rate meal", () -> new RatingMealUI().show()));
        menu.add(new MenuItem(CANCEL_BOOKING, "Cancel booking", () -> new CancelBookingUI().show()));
        menu.add(new MenuItem(LIST_MENU, "List Menu", () -> new ListMenuUI().show()));
        menu.add(new MenuItem(CHECK_BOOKINGS, "Check Bookings of Current User", () -> new CheckBookingsByUserUI().show()));
        menu.add(new MenuItem(CONSULT_MEAL_RATING, "consult meal rating", () -> new ConsultMealRatingUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
                new ShowMessageAction("Not implemented yet")));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildAlergenProfileMenu() {
        final Menu menu = new Menu("Alergen Profile");
        menu.add(new MenuItem(CREATE_ALERGEN_PROFILE_OPTION, "add new alergen to the profile", new CreateAlergenProfileAction()));
        return menu;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
