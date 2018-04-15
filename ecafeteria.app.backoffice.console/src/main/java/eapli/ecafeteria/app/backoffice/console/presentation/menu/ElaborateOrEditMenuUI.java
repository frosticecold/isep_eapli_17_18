/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.ElaborateOrEditMenuController;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Map;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String headline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {

        Menu m = askForWorkingPeriod();
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
        String initialDate = "", endDate = "";
        /**
         * Ask initial date and end date
         */
        /**
         * Read input data
         */
        Menu menu = theController.createOrFindMenu(initialDate, endDate, DATE_INPUT_FORMAT);
        return menu;
    }

    /**
     * Show workingDays of a Menu
     * <p>
     * Execution order : 2
     *
     */
    private void showDaysOfPeriod(Menu m, Map<Integer, Calendar> workWeek) {
        /**
         * Show days
         */

    }

    /**
     * Ask for Working day for a Menu Period
     * <p>
     * Execution order : 3
     */
    private Calendar askForWorkingDay(Menu m, Map<Integer, Calendar> workWeek) {

        /**
         * show working days
         */
        /**
         * ask for working day
         */
        int day = 0;
        Calendar cal = workWeek.get(day);
        /**
         * Send working day
         */
        theController.selectDay(cal);

        return cal;
    }

    /**
     * Show Meals
     * <p>
     * Execution order : 4
     *
     * @param m
     * @return
     */
    private Meal addMeals(Meal m) {

        return null;
    }

}
