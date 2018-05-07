/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealUI extends CafeteriaUserBaseUI {

    private final BookingMealController controller = new BookingMealController();

    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {

        //====================================SAVE DAY============================================
        Calendar cal = Console.readCalendar("Insert desired day (DD-MM-YYYY)");

        if (controller.is24hBefore(cal) == false) {
            System.out.println("Only avaliable to do a booking in 24hours before the meal date!");
            return false;
        }

        //====================================lIST MEAL============================================
        Iterable<Meal> mealList = null;
        List<Meal> listMeal = new ArrayList<Meal>();
        Meal choosedMeal = null;

        int option = 0;

        System.out.println("Choose Meal Type:\n1-Lunch\n2-Dinner");

        option = Console.readInteger("");

        switch (option) {
            case 1:
                mealList = controller.listMeals(cal, MealType.LUNCH);
                if (controller.mealListIsEmpty(mealList)) {
                    System.out.println("\nThere are no meals in that conditions.\n");
                    return false;
                }
                
                if (controller.mealListIsEmpty(mealList)) {
                    System.out.println("\nThere are no meals in that conditions.\n");
                    return false;
                }
                listMeal = controller.mealListIfMenuIsPublic(mealList);

                if (!controller.menuOfMealListIsPublic(listMeal)) {
                    System.out.println("\nThere are no meals published.\n");
                    return false;
                }

                SelectWidget<List<Meal>> selectWidget
                        = new SelectWidget("Select a meal", listMeal);
                selectWidget.show();
                try {
                    int index = selectWidget.selectedOption() - 1;
                    if (index == -1) {
                        return false;
                    }

                    choosedMeal = controller.selectMeal(index, listMeal);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return false;
                }

                break;

            case 2:
                mealList = controller.listMeals(cal, MealType.DINNER);

                if (controller.mealListIsEmpty(mealList)) {
                    System.out.println("\nThere are no meals in that conditions.\n");
                    return false;
                }

                if (controller.mealListIsEmpty(mealList)) {
                    System.out.println("\nThere are no meals in that conditions.\n");
                    return false;
                }
                listMeal = controller.mealListIfMenuIsPublic(mealList);

                if (!controller.menuOfMealListIsPublic(listMeal)) {
                    System.out.println("\nThere are no meals published.\n");
                    return false;
                }

                SelectWidget<List<Meal>> selectWidget2
                        = new SelectWidget("Select a meal", listMeal);
                selectWidget2.show();
                try {
                    int index = selectWidget2.selectedOption() - 1;
                    if (index == -1) {
                        return false;
                    }

                    choosedMeal = controller.selectMeal(index, listMeal);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return false;
                }

                break;

            default:
                System.out.println("Invalid Option");
                return false;

        }

        //====================================CONFIRM AND SAVE THE CHOOSED MEAL==================================
        if (choosedMeal == null) {
            System.out.println("Invalid meal choice!");
            return false;
        }

        System.out.println("\nChoosed Meal:");
        System.out.println(choosedMeal.toString());

        //===================================SHOW ALERGEN AND CALORICS==================================
        System.out.println("\nAlergen Info:\n");
        if (controller.mealHasAlergens(choosedMeal)) {
           // for (Meal meal : listMeal) {
                System.out.println(choosedMeal.dish().alergenInDish().toString());
            //}
        } else {
            System.out.println("Dish doesn't have alergens.\n");
        }

        //===================================PAYMENT==================================
        System.out.println("Do you want to continue?\n1-Yes\n2-No\n");

        int option2 = 0;

        option2 = Console.readInteger("");

        switch (option2) {
            case 1:
                try {
                    if (controller.doTransaction(AuthorizationService.session().authenticatedUser().id(), choosedMeal)) {
                        System.out.println("Success, the transaction was done.");
                    }
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            case 2:
                System.out.println("Operation closed.");
                break;

            default:
                System.out.println("Invalid Option");
                return false;

        }

        //====================================SAVE IN DATABASE==================================
        BookingState bookingState = new BookingState();

        try {

            controller.persistBooking(AuthorizationService.session().authenticatedUser().id(), cal, bookingState, choosedMeal);

        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(BookingMealUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return super.headline();
    }

}
