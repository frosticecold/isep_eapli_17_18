/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.AddAlergenAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishNutricionalInfoAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishPriceAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.CreateAlergenAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListAlergensAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryAsTuplesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerDishTypeUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportHighCaloriesDishesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.ListDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.RegisterDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.CloseMenuPlanAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.CreateMenuPlanAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.EndShiftAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.ListMaterialAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.RegisterBatchUsedInMealAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.RegisterMadeMealsAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.RegisterMaterialAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting.ReportBookingPerDateUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting.ReportBookingPerDishUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting.ReportBookingPerMealUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting.SearchBatchUsageAction;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.BookedMealsReportingUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.CopyMenuUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.ElaborateOrEditMenuUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.MainMenuDeliveredMealsReportingUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.MealsByMenuPlanUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.PublishMenuUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.showDishAcceptanceRateUI;
import eapli.ecafeteria.application.KitchenAlert.KitchenAlertController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.kitchen.KitchenWatchDogo;
import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_REGISTER_DTO_OPTION = 7;
    private static final int DISH_LIST_DTO_OPTION = 8;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 9;
    private static final int DISH_CHANGE_OPTION = 10;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;
    private static final int ADD_ALERGEN_OPTION = 3;
    // ALERGENS
    private static final int CREATE_ALERGEN_OPTION = 1;
    private static final int LIST_ALERGEN_OPTION = 2;
    // KITCHEN
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;
    private static final int REGISTER_BATCH_USED_IN_MEAL = 3;
    private static final int KITCHEN_REGISTER_MADE_MEALS = 4;
    private static final int KITCHEN_LIST_MEALS_BY_BATCH_OPTION = 5;
    private static final int KITCHEN_CREATE_OR_EDIT_MENUPLAN = 6;
    private static final int KITCHEN_CLOSE_MENU_PLAN = 7;
    private static final int KITCHEN_END_SHIFT = 9;

    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    //BOOKING REPORTING
    private static final int REPORTING_BOOKING_SUB_MENU = 8;
    private static final int REPORTING_BOOKING_PER_DATE = 1;
    private static final int REPORTING_BOOKING_PER_PLATE = 2;
    private static final int REPORTING_BOOKING_PER_MEAL = 3;

    // MENU
    private static final int MENU_EDIT_CREATE_OPTION = 1;
    private static final int MENU_COPY_OPTION = 2;
    private static final int MENU_PUBLISH_OPTION = 3;
    private static final int MENU_PREVIEW_OPTION = 4;

    //PREVISIONS REPORTING
    private static final int PREVISIONS_BOOKEDMEALS_OPTION = 1;
    private static final int PREVISIONS_MEALMENUPLAN_OPTION = 2;
    private static final int PREVISIONS_DELIVEREDMEALS_OPTION = 3;
    private static final int PREVISIONS_RATING_OPTION = 4;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int MENU_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_TYPES_OPTION = 5;
    private static final int ALERGEN_OPTION = 6;
    private static final int TRACEABILITY_OPTION = 7;
    private static final int REPORTING_DISHES_OPTION = 8;
    private static final int REPORTING_BOOKING_OPTION = 9;
    private static final int PREVISIONSREPORTING_OPTION = 10;

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
        return "eCafeteria Back Office [@" + AuthorizationService.session().authenticatedUser().id()
                + "]";
    }

    private Menu buildMainMenu() {

        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(
                new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }
        //==========================ADMIN MENU==================
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.ADMINISTER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.add(
                    new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu,
                    new ShowVerticalSubMenuAction(settingsMenu)));
        }
        //==========================KITCHEN MENU==================
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_KITCHEN)) {
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.add(new SubMenu(TRACEABILITY_OPTION, kitchenMenu,
                    new ShowVerticalSubMenuAction(kitchenMenu)));
        }
        //==========================MANAGE MENU==================
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_MENUS)) {
            final Menu menuOfMenus = buildMenuOfMenus();
            mainMenu.add(new SubMenu(MENU_OPTION, menuOfMenus,
                    new ShowVerticalSubMenuAction(menuOfMenus)));

            final Menu dishTypeMenu = buildDishMenu();
            mainMenu.add(new SubMenu(DISH_TYPES_OPTION, dishTypeMenu,
                    new ShowVerticalSubMenuAction(dishTypeMenu)));

            final Menu alergenMenu = buildAlergenMenu();
            mainMenu.add(new SubMenu(ALERGEN_OPTION, alergenMenu,
                    new ShowVerticalSubMenuAction(alergenMenu)));
            // reporting
            final Menu reportingDishesMenu = buildReportingDishesMenu();
            mainMenu.add(new SubMenu(REPORTING_DISHES_OPTION, reportingDishesMenu,
                    new ShowVerticalSubMenuAction(reportingDishesMenu)));
            //previsions reporting
            final Menu previsionsReportingMenu = buildPrevisionsReportingMenu();
            mainMenu.add(new SubMenu(PREVISIONSREPORTING_OPTION, previsionsReportingMenu,
                    new ShowVerticalSubMenuAction(previsionsReportingMenu)));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.add(new MenuItem(ADD_USER_OPTION, "Add User", () -> new AddUserUI().show()));
        menu.add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
        menu.add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User",
                new DeactivateUserAction()));
        menu.add(new MenuItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");

        // dish types
        menu.add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type",
                new RegisterDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type",
                new ListDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description",
                new ChangeDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction()));

        // dishes
        menu.add(new MenuItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction()));
        menu.add(new MenuItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction()));

        menu.add(new MenuItem(DISH_REGISTER_DTO_OPTION, "Register new Dish (via DTO)",
                () -> new RegisterDishViaDTOUI().show()));
        menu.add(new MenuItem(DISH_LIST_DTO_OPTION, "List all Dish (via DTO)",
                () -> new ListDishViaDTOUI().show()));

        menu.add(new MenuItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction()));
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.add(new MenuItem(DISH_CHANGE_OPTION, "Change Dish Information",
                new ShowVerticalSubMenuAction(changeDishMenu)));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildAlergenMenu() {
        final Menu menu = new Menu("Alergens >");
        menu.add(new MenuItem(CREATE_ALERGEN_OPTION, "Create an Alergen",
                new CreateAlergenAction()));
        menu.add(new MenuItem(LIST_ALERGEN_OPTION, "List all Alergens",
                new ListAlergensAction()));
        return menu;
    }

    private Menu buildKitchenMenu() {
        final Menu menu = new Menu("Traceability >");

        menu.add(new MenuItem(MATERIAL_REGISTER_OPTION, "Register new material",
                new RegisterMaterialAction()));

        menu.add(new MenuItem(MATERIAL_LIST_OPTION, "List all materials", new ListMaterialAction()));

        menu.add(new MenuItem(REGISTER_BATCH_USED_IN_MEAL, "Register batch used in meal", new RegisterBatchUsedInMealAction()));

        menu.add(new MenuItem(KITCHEN_REGISTER_MADE_MEALS, "Register Made Meals", new RegisterMadeMealsAction()));

        menu.add(new MenuItem(KITCHEN_LIST_MEALS_BY_BATCH_OPTION, "List Meals by batch", new SearchBatchUsageAction()));

        menu.add(new MenuItem(KITCHEN_CREATE_OR_EDIT_MENUPLAN, "Create or edit menuplan", new CreateMenuPlanAction()));

        menu.add(new MenuItem(KITCHEN_CLOSE_MENU_PLAN, "Close menuplan", new CloseMenuPlanAction()));

        final Menu kitchenReportMenu = buildBookingReportingMenu();
        menu.add(new SubMenu(REPORTING_BOOKING_SUB_MENU, kitchenReportMenu, new ShowVerticalSubMenuAction(kitchenReportMenu)));

        menu.add(new MenuItem(KITCHEN_END_SHIFT, "End Shift", new EndShiftAction()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildChangeDishMenu() {
        final Menu menu = new Menu("Change Dish >");

        menu.add(new MenuItem(CHANGE_DISH_NUTRICIONAL_INFO_OPTION, "Change Nutricional Info",
                new ChangeDishNutricionalInfoAction()));
        menu.add(new MenuItem(CHANGE_DISH_PRICE_OPTION, "Change Price",
                new ChangeDishPriceAction()));
        menu.add(new MenuItem(ADD_ALERGEN_OPTION, "Add Alergen",
                new AddAlergenAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildReportingDishesMenu() {
        final Menu menu = new Menu("Reporting Dishes >");

        menu.add(new MenuItem(REPORTING_DISHES_PER_DISHTYPE_OPTION, "Dishes per Dish Type",
                () -> new ReportDishesPerDishTypeUI().show()));
        menu.add(new MenuItem(REPORTING_HIGH_CALORIES_DISHES_OPTION, "High Calories Dishes",
                () -> new ReportHighCaloriesDishesUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION,
                "Dishes per Caloric Category",
                () -> new ReportDishesPerCaloricCategoryUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION + 1,
                "Dishes per Caloric Category (as tuples)",
                () -> new ReportDishesPerCaloricCategoryAsTuplesUI().show()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildMenuOfMenus() {
        final Menu menu = new Menu("Menus >");

        menu.add(new MenuItem(MENU_EDIT_CREATE_OPTION, "Edit/Create Menu", () -> new ElaborateOrEditMenuUI().show()));
        menu.add(new MenuItem(MENU_COPY_OPTION, "Copy Menu", () -> new CopyMenuUI().show()));
        menu.add(new MenuItem(MENU_PUBLISH_OPTION, "Publish Menus", () -> new PublishMenuUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildBookingReportingMenu() {
        final Menu menu = new Menu("Booking Reporting Menu ");

        menu.add(new MenuItem(REPORTING_BOOKING_PER_DATE, "Booking per Date",
                () -> new ReportBookingPerDateUI().show()));
        menu.add(new MenuItem(REPORTING_BOOKING_PER_PLATE, "Booking per Plate",
                () -> new ReportBookingPerDishUI().show()));
        menu.add(new MenuItem(REPORTING_BOOKING_PER_MEAL, "Booking per Meal",
                () -> new ReportBookingPerMealUI().show()));
        return menu;
    }

    private Menu buildPrevisionsReportingMenu() {

        final Menu menu = new Menu("Previsions Reporting Menu");

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        menu.add(new MenuItem(PREVISIONS_BOOKEDMEALS_OPTION, "Show all Booked Meals",
                () -> new BookedMealsReportingUI().show()));
        menu.add(new MenuItem(PREVISIONS_MEALMENUPLAN_OPTION, "Show all Meals by MenuPlan",
                () -> new MealsByMenuPlanUI().show()));
        menu.add(new MenuItem(PREVISIONS_DELIVEREDMEALS_OPTION, "Show delivered meals menu",
                () -> new MainMenuDeliveredMealsReportingUI().show()));
        menu.add(new MenuItem(PREVISIONS_RATING_OPTION, "Show Ratings and Dishes Acceptance Rate",
                () -> new showDishAcceptanceRateUI().show()));

        return menu;
    }
}
