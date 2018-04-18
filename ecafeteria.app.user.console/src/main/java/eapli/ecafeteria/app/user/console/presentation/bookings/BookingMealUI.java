/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealUI extends AbstractUI {

    private final BookingMealController controller = new BookingMealController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Calendar cal = Console.readCalendar("Insert desired day (DD-MM-YYYY)");


        Iterable<Meal> mealList = null;

        //final String mealType = Console.readLine("Insert 1 or 2:\n");
        int option = 0;

        System.out.println("Choose Meal Type:\n1-Lunch\n2-Dinner");
                
        
        do {
            option = Console.readInteger("");
                   
            switch (option) {
                case 1:
                    mealList = controller.listMeals(cal, MealType.LUNCH);
                    break;
                case 2:
                    mealList = controller.listMeals(cal, MealType.DINNER);
                    break;

                case 0:
                    break;
            }
        } while (option != 0);

        
        System.out.println("Choose one meal");
        final Long id = Console.readLong("Insert the meal id:\n");

        Meal choosedMeal = null;

        for (Meal meal : mealList) {
            if (meal.id() == id) {
                choosedMeal = meal;
            } else {
                System.out.println("Id inválido");
                return false;
            }
        }

        System.out.println("Nutricional Info:");
        controller.showNutricionalInfo(choosedMeal);

        controller.doTransaction(AuthorizationService.session().authenticatedUser().id(), choosedMeal);

        BookingState bookingState = new BookingState();

        try {

            controller.persistBooking(AuthorizationService.session().authenticatedUser().id(), cal.getTime(), bookingState, choosedMeal);

        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
