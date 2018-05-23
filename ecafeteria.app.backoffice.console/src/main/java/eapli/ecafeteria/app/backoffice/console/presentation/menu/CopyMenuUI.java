/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.CopyMenuController;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class CopyMenuUI extends AbstractUI {

    private CopyMenuController controller = new CopyMenuController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        SelectWidget<Menu> widget = new SelectWidget<>("Select a menu:\n", controller.getAvailableMenus());

        try {
            boolean editing = true;
            widget.show();
            if (widget.selectedElement() != null) {
                Menu oldMenu = widget.selectedElement();

                Calendar startingDay = Console.readCalendar("Insert Start Date");
                Calendar endingDay = Console.readCalendar("Insert end Date");
                Menu newMenu = new Menu(startingDay, endingDay);
                Iterable<Meal> meals = controller.findAllMeals(oldMenu);
                Iterable<Meal> newMealsToSave = controller.changeMealsToNewMenu(meals, newMenu, oldMenu);

                for (Meal meal : newMealsToSave) {
                    System.out.println(meal.toString());
                }

                do {
                    Calendar calendar = askAndSelectWorkingDay(newMenu);
                    if (calendar != null) {
                        menuAddOrRemoveMeals(newMenu, calendar);
                        editing = Console.readBoolean("Keep editing? Y/N");
                    } else {
                        editing = false;
                    }
                } while (editing);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return true;
    }

    private Menu askForWorkingPeriod() throws DataIntegrityViolationException, DataConcurrencyException {
        boolean runagain = true;
        Menu menu;
        Calendar initialDate, endDate;
        do {
            System.out.println("\nAll dates are in dd-MM-yyyy format.");
            initialDate = Console.readCalendar("Please enter the new initial date:");
            endDate = Console.readCalendar("Please enter the new end date:");
            menu = controller.createOrFindMenu(initialDate, endDate);
            if (menu == null) {
                System.out.println("Error creating menu.");
            }
            runagain = false;
        } while (runagain);
        return menu;
    }

    private Calendar askAndSelectWorkingDay(final Menu m) {

        Calendar cal = null;
        boolean runagain = true;
        Map<Integer, Calendar> workWeekDays = m.getWorkWeekDays();
        List<String> list = new ArrayList<>();
        for (Calendar c : workWeekDays.values()) {
            list.add(DateTime.convertCalendarToDayMonthYearAndDayName(c));
        }
        SelectWidget<String> widget = new SelectWidget<>("Showing Working days", list);

        do {
            widget.show();
            int option = widget.selectedOption();
            if (option <= 0) {
                break;
            }
            option--;
            cal = workWeekDays.get(option);
            runagain = false;
            controller.selectDay(cal);
        } while (runagain);
        return cal;
    }

    private void menuAddOrRemoveMeals(final Menu menu, final Calendar day) {
        boolean editing = true;
        ListWidget<Meal> widget;
        do {
            widget = new ListWidget<>("List Meal", MenuService.getMealsFromMenuByGivenDay(menu, day));
            widget.show();
            System.out.println("1)Add\n2)Remove\n0)Exit");
            int answer = Console.readInteger("Please input option");

            switch (answer) {
                case 1:
                    menuAddMeals(menu, day);
                    break;
                case 2:
                    menuRemoveMeal(menu, day);
                    break;
                default:
                    editing = false;
                    break;
            }

        } while (editing);
    }

    private void menuAddMeals(final Menu menu, final Calendar day) {
        boolean adding = true;
        ListWidget<Meal> widgetmeals;
        SelectWidget<DishType> widgetdishtype = new SelectWidget<>("Select DishType", controller.getDishTypes());
        SelectWidget<Dish> widgetdish;
        SelectWidget<MealType> widgetmealtype = new SelectWidget<>("Select MealType", Arrays.asList(MealType.values()));
        do {
            widgetdishtype.show();
            DishType dt = widgetdishtype.selectedElement();
            if (dt == null) {
                break;
            }
            widgetdish = new SelectWidget<>("Select Dish", controller.getDishesByDishType(dt));
            widgetdish.show();
            Dish dish = widgetdish.selectedElement();
            if (dish == null) {
                break;
            }
            widgetmeals = new ListWidget<>("List Meal", MenuService.getMealsFromMenuByGivenDay(menu, day));
            widgetmealtype.show();
            MealType mt = widgetmealtype.selectedElement();
            if (mt == null) {
                break;
            }
            Meal meal = new Meal(dish, mt, day, menu);
            System.out.println(meal);
            boolean confirm = Console.readBoolean("Confirm meal? Y/N");
            if (confirm) {
                try {
                    controller.addMealOnMenu(menu, meal);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Error saving on database" + ex.getMessage());
                }
            }
            adding = Console.readBoolean("Add more? Y/N");
            if (adding) {
                widgetmeals.show();
            }
        } while (adding);
    }

    private void menuRemoveMeal(Menu menu, Calendar day) {
        SelectWidget<Meal> widgetmeal;
        boolean removing = true;
        do {
            widgetmeal = new SelectWidget<>("Select Meal", MenuService.getMealsFromMenuByGivenDay(menu, day));
            /**
             * Ask what to remove
             */
            widgetmeal.show();
            Meal meal = widgetmeal.selectedElement();
            if (meal == null) {
                break;
            }
            boolean areyousure = Console.readBoolean("Are you sure? Y/N");
            if (areyousure) {
                try {
                    controller.removeMealFromMenu(menu, meal);
                } catch (DataIntegrityViolationException ex) {
                    System.out.println("Error deleting on database" + ex.getMessage());
                }
            }
            removing = Console.readBoolean("Remove more? Y/N");
        } while (removing);
    }

    @Override
    public String headline() {
        return "Copy Menu";
    }
}
