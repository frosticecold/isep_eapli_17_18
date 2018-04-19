/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.domain.booking.BookingState;
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

        //====================================SAVE DAY============================================
        Calendar cal = Console.readCalendar("Insert desired day (DD-MM-YYYY)");

        //====================================lIST MEAL============================================
        Iterable<Meal> mealList = null;
        int option = 0;

        System.out.println("Choose Meal Type:\n1-Lunch\n2-Dinner");

        do {
            option = Console.readInteger("");
            if(option > 0 && option <= MealType.values().length)
                option--;
            
            mealList = controller.listMeals(cal, MealType.values()[option]);

        } while (option != 0);

       
        if(!mealList.iterator().hasNext()){
            System.out.println("\nThere is no meal with that conditions\n");
            return false;
        }
        
        //====================================CONFIRM AND SAVE THE CHOOSED MEAL==================================
        System.out.println("Choose one meal");
        final Long id = Console.readLong("Insert the meal id:\n");

        Meal choosedMeal = null;

        for (Meal meal : mealList) {
            if (meal.id().equals(id)) {
                choosedMeal = meal;
            } else {
                System.out.println("Id inválido");
                return false;
            }
        }

        //===================================SHOW NUTRICIONAL INFO AND CALORICS==================================
        System.out.println("Alergen Info:\n");
        controller.showAlergen(choosedMeal);
        System.out.println("Nutricional Info:\n");
        controller.showNutricionalInfo(choosedMeal);

        //===================================Paymemnt==================================
        System.out.println("Do you want to continue?\n1-Yes\n2-No\n");

        int option2 = 0;

        do {
            option2 = Console.readInteger("");

            switch (option2) {
                case 1:
                    if (controller.doTransaction(AuthorizationService.session().authenticatedUser().id(), choosedMeal) == true) {
                        System.out.println("Operação bem sucedida, foi efetuado o pagamento");
                    } else {
                        System.out.println("Operação encerrada");
                        break;
                    }
                case 2:
                    break;

                case 0:
                    break;
            }
        } while (option2 != 0);

        //====================================SAVE IN DATABASE==================================
        BookingState bookingState = new BookingState();

        try {

            controller.persistBooking(AuthorizationService.session().authenticatedUser().id(), cal.getTime(), bookingState, choosedMeal);

        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
