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
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        //====================================lIST MEAL============================================
        Iterable<Meal> mealList = null;
        List<Meal> listMeal = new ArrayList<>();
        Meal choosedMeal = null;
        Username user = AuthorizationService.session().authenticatedUser().id();
        List<Booking> bookings = controller.listBookingsOfUser(user);

        int option = 0;

        do {
            System.out.println("Choose Meal Type:\n1-Lunch\n2-Dinner");
            option = Console.readInteger("");
        } while (option != 1 && option != 2);

        MealType mealType = null;
        if (option == 1) {
            mealType = MealType.LUNCH;
        } else {
            mealType = MealType.DINNER;

        }

        mealList = controller.listMeals(cal, mealType);
        Calendar choosedDate = cal;
        if (controller.is24hBeforeMeal(choosedDate, mealType) == false) {
            System.out.println("Only avaliable to do a booking in 24hours before the meal date!");
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

        //====================================CONFIRM AND SAVE THE CHOOSED MEAL==================================
        System.out.println("\nChoosed Meal:");
        System.out.println(choosedMeal.toString());

        //===================================SHOW ALERGEN AND CALORICS==================================
        System.out.println(
                "\nAlergen Info:\n");
        if (controller.mealHasAlergens(choosedMeal)) {
            System.out.println(choosedMeal.dish().alergenInDish().toString());
        } else {
            System.out.println("Dish doesn't have alergens.\n");
        }

        //===================================PAYMENT AND PERSIST==================================
        int option2 = 0;

        do {
            System.out.println("Do you want to continue?\n1-Yes\n2-No\n");
            option2 = Console.readInteger("");
        } while (option2 != 1 && option2 != 2);

        if (option2 == 1) {

            if (!controller.hasEnoughMoney(user, choosedMeal)) {
                System.out.println("USER HASN'T ENOUGH MONEY\nPlease charge your card before booking a meal");
                return false;
            } else {
                try {

                    BookingState bookingState = new BookingState();
                    if (controller.confirmBooking(user, cal, bookingState, choosedMeal, bookings)) {
                        System.out.println("Success. Booking was created.");
                    } else {
                        System.out.println("You already booked this meal!");
                        System.out.println("Error occured. Not possible to "
                                + "book this meal.");
                    }
                } catch (Exception e) {
                    System.out.println("Error while saving. Please check your connection.\n"
                            + e.getMessage());
                }
            }
        } else if (option2 == 2) {

            System.out.println("Operation closed.");

        }

        return true;
    }

    @Override
    public String headline() {
        return super.headline();
    }

}
