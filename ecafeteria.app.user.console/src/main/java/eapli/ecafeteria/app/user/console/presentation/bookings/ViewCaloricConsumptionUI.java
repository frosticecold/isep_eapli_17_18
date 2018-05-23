/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.ViewCaloricConsumptionController;
import eapli.framework.util.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewCaloricConsumptionUI extends CafeteriaUserBaseUI {

    /**
     * ViewCaloricConsumptionController instance
     */
    private ViewCaloricConsumptionController controller = new ViewCaloricConsumptionController();

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {
        
        final Calendar initialDate = Console.readCalendar("Insert desired starting date (DD-MM-YYYY):\n");
        final Calendar finalDate = Console.readCalendar("Insert desired ending date (DD-MM-YYYY):\n");

        double calories = controller.getCaloricConsuption(initialDate, finalDate);

        if (calories >= 0) {
            System.out.println("\nTotal caloric consumption: " + calories + " KCal");
        } else {
            System.out.println("There was a problem while calculating the caloric consumption! ");
            return false;
        }

        return true;
    }

    @Override
    public String headline() {
        return super.headline();
    }

}
