/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.ElaborateOrEditMenuController;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ElaborateOrEditMenuUI extends AbstractUI {

    private ElaborateOrEditMenuController theController = new ElaborateOrEditMenuController();

    private static final String DATE_INPUT_FORMAT = DateTime.DEFAULT_SIMPLE_DATA_FORMAT;

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        Menu menu = askForWorkingPeriod();
        boolean editing = true;
        do {
            Calendar calendar = askAndSelectWorkingDay(menu);
            menuAddOrRemoveMeals(menu, calendar);
            askForConfirmation(menu);
            editing = Console.readBoolean("Keep editing? Y/N");
        } while (editing);
        return true;
    }

    @Override
    public String headline() {
        return "Elaborate Menu";
    }

    /**
     * Method that asks for a working Period
     * <p>
     * Execution order : 1
     *
     * @author Raúl Correia
     * @return
     */
    private Menu askForWorkingPeriod() {
        /**
         * Ask initial date and end date
         */
        boolean runagain = true;
        Menu menu;
        Calendar initialDate, endDate;
        do {
            System.out.println("All dates are in dd-MM-yyy format.");
            initialDate = Console.readCalendar("Please enter the initial date:");
            endDate = Console.readCalendar("Please enter the end date:");
            /**
             * Read input data
             */
            menu = theController.createOrFindMenu(initialDate, endDate);
            if (menu == null) {
                System.out.println("Error creating menu.");
            }
            runagain = false;
        } while (runagain);
        return menu;
    }

    /**
     * Ask for Working day for a Menu Period
     * <p>
     * Execution order : 3
     */
    private Calendar askAndSelectWorkingDay(Menu m) {

        Calendar cal = null;
        boolean runagain = true;
        Map<Integer, Calendar> workWeekDays = m.getWorkWeekDays();
        List<String> list = new ArrayList<>();
        for (Calendar c : workWeekDays.values()) {
            list.add(DateTime.convertCalendarToDayMonthYearAndDayName(c));
        }
        SelectWidget<String> widget = new SelectWidget<>("Showing Working days", list);
        /**
         * show working days
         */
        do {
            widget.show();
            int option = widget.selectedOption();
            if (option <= 0) {
                break;
            }
            option--;
            /**
             * ask for working day
             */
            cal = workWeekDays.get(option);
            runagain = false;
            //Calendar cal = workWeek.get(day);
            /**
             * Send working day
             */
            theController.selectDay(cal);
        } while (runagain);
        return cal;
    }

    /**
     * Ask to add or remove meals
     *
     * @param menu
     */
    private void menuAddOrRemoveMeals(Menu menu, Calendar day) {
        boolean editing = true;
        ListWidget<Meal> widget = new ListWidget<>("List Meal", menu.getMealsByDay(day));
        do {
            widget.show();
            /**
             * Ask for add or remove
             */
            System.out.println("1)Add\n2)Remove\n0)Exit");
            int answer = Console.readInteger("Please input option");

            switch (answer) {
                case 1:
                    /**
                     * if 1 = Add
                     */
                    menuAddMeals(menu, day);
                    break;
                case 2:
                    /**
                     * if 2 = Remove
                     *
                     */
                    menuRemoveMeal(menu, day);
                    break;
                default:
                    /**
                     * Else break
                     */
                    editing = false;
                    break;
            }

        } while (editing);
    }

    /**
     * Add
     *
     * @param menu
     * @return
     */
    private void menuAddMeals(Menu menu, Calendar day) {
        boolean adding = true;
        ListWidget<Meal> widgetmeals = new ListWidget<>("List Meal", menu.getMealsByDay(day));
        SelectWidget<DishType> widgetdishtype = new SelectWidget<>("Select DishType", theController.getDishTypes());
        SelectWidget<Dish> widgetdish;
        SelectWidget<MealType> widgetmealtype = new SelectWidget<>("Select MealType", Arrays.asList(MealType.values()));
        do {
            widgetdishtype.show();
            DishType dt = widgetdishtype.selectedElement();
            if (dt == null) {
                break;
            }
            widgetdish = new SelectWidget<>("Select Dish", theController.getDishesByDishType(dt));
            widgetdish.show();
            Dish dish = widgetdish.selectedElement();
            if (dish == null) {
                break;
            }
            widgetmealtype.show();
            MealType mt = widgetmealtype.selectedElement();
            if (mt == null) {
                break;
            }
            Meal meal = new Meal(dish, mt, day);
            System.out.println(meal);
            boolean confirm = Console.readBoolean("Confirm meal? Y/N");
            if (confirm) {
                menu.addMeal(meal);
            }
            adding = Console.readBoolean("Add more? Y/N");
            if (adding) {
                widgetmeals.show();
            }
        } while (adding);
    }

    /**
     * Remove
     */
    private void menuRemoveMeal(Menu menu, Calendar day) {
        SelectWidget<Meal> widgetmeal;
        boolean removing = true;
        do {
            widgetmeal = new SelectWidget<>("Select Meal", menu.getMealsByDay(day));
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
                menu.removeMeal(meal);
            }
            removing = Console.readBoolean("Remove more? Y/N");
        } while (removing);
    }

    private void askForConfirmation(Menu m) {
        /**
         * Ask for confirmation Y/N
         */
        boolean save = Console.readBoolean("Persist all your actions? Y/N");
        if (save) {
            try {
                Menu othermenu = theController.saveMenu(m);
                if (othermenu == null) {
                    System.out.println("Problems saving menu...");

                }
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(ElaborateOrEditMenuUI.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (DataConcurrencyException ex) {
                Logger.getLogger(ElaborateOrEditMenuUI.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
