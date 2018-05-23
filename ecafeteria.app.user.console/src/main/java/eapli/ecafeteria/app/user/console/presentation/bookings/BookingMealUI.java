package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.application.booking.WatchDogAlert;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.NoResultException;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealUI extends CafeteriaUserBaseUI implements Observer {

    private final BookingMealController controller = new BookingMealController();
    private final WatchDogAlert watchDog = new WatchDogAlert();
    private boolean limitAlert = true;

    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {
        controller.addObserver(watchDog);
        watchDog.addObserver(this);
        //====================================SAVE DAY============================================
        Calendar cal = Console.readCalendar("Insert desired day (DD-MM-YYYY)");
        //====================================lIST MEAL============================================
        Iterable<Meal> mealList = null;
        List<Meal> listMealPublish = new ArrayList<>();
        Meal choosedMeal = null;
     

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

        if (controller.isAlreadyBooked(mealType, cal)) {
            System.out.println("You already booked a meal for this date and this mealtype!");
            return false;
        }

        Calendar choosedDate = cal;
        if (controller.is24hBeforeMeal(choosedDate, mealType) == false) {
            System.out.println("Only avaliable to do a booking in 24hours before the meal date!");
            return false;
        }

        mealList = controller.listMeals(cal, mealType);

        if (controller.mealListIsEmpty(mealList)) {
            System.out.println("\nThere are no meals in that conditions.\n");
            return false;
        }

        listMealPublish = controller.mealListIfMenuIsPublic(mealList);

        if (!controller.menuOfMealListIsPublic(listMealPublish)) {
            System.out.println("\nThere are no meals published.\n");
            return false;
        }

        SelectWidget<List<Meal>> selectWidget
                = new SelectWidget("Select a meal", listMealPublish);
        selectWidget.show();
        try {
            int index = selectWidget.selectedOption() - 1;
            if (index == -1) {
                return false;
            }

            choosedMeal = controller.selectMeal(index, listMealPublish);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        //====================================CONFIRM AND SAVE THE CHOOSED MEAL==================================
        System.out.println("\nChoosed Meal:");
        System.out.println(choosedMeal.toString());

        //===================================SHOW ALERGEN AND CALORICS==================================
        System.out.println("»»» Allergens present in the dish: ");
        if (controller.mealHasAlergens(choosedMeal)) {
            for (Alergen alergen : choosedMeal.dish().alergenInDish()) {
                System.out.println("» " + alergen.toString());
            }
        } else {
            System.out.println("Dish doesn't have alergens.");
        }

        /*
        Verify if user has any allergens to the dish
        If user doesn't have any matching allergen -> print an informative message and move with the program.
        If the user, in fact, has matched allergens, display them and ask if the user still wishes to proceed with the booking.
        If input is 1 -> Proceed with reservation, else if input is 2 -> Cancel reservation 
        If user does not have an allergen profile, leave a simple message.
        @autor Rui Almeida <1160818>
         */
        int option2 = 1;

        try {

            //Matches allergens between the dish and the user
            List<Alergen> matchedAllergens = controller.matchAllergens(choosedMeal);

            //Verify if user has any matched allergens
            if (matchedAllergens.isEmpty() && controller.mealHasAlergens(choosedMeal)) {

                System.out.println("\n»»» You have no allergens to the dish.");

            } else if (controller.mealHasAlergens(choosedMeal)) {

                //If the user has allergens, display them.
                System.out.println("\n»»» You have " + matchedAllergens.size() + " allergen(s) that overlap with the dish's: ");
                for (int i = 0; i < matchedAllergens.size(); i++) {
                    System.out.println((i + 1) + ". " + matchedAllergens.get(i).toString() + ".");
                }
            }

        } catch (NoResultException ex) {
            System.out.println("\n»»» User does not have an allergen profile!");
        }

        //Ask user if he wants to proceed with the booking and proceed accordingly.
        do {
            option2 = Console.readInteger("\n»»» Do you still wish to proceed with the booking? ( 1. Yes / 2. No )");
        } while (option2 != 1 && option2 != 2);

        //===================================PAYMENT AND PERSIST==================================
        if (option2 == 1) {

            if (!controller.hasEnoughMoney(choosedMeal)) {
                System.out.println("USER HASN'T ENOUGH MONEY\nPlease charge your card before booking a meal");
                return false;
            } else {
                try {
                    BookingState bookingState = new BookingState();
                    if (controller.confirmBooking( cal, bookingState, choosedMeal)) {
                        System.out.println("Success. Booking was created.");
                        if (!limitAlert) {
                            System.out.println("===========<<<<<<<<<<ALERT>>>>>>>>>>===========");
                            System.out.println("|Your balance is under defined balance limits!|");
                            System.out.println("===============================================");
                        }
                    } else {
                        //System.out.println("You already booked a meal for this date and this mealtype!");
                        System.out.println("Error occured. Not possible to "
                                + "book this meal.");
                    }
                } catch (Exception e) {
                    System.out.println("Error while saving. Please check your connection.\n"
                            + e.getMessage());
                }
            }
        } else {

            System.out.println("Operation closed.");

        }

        return true;
    }

    @Override
    public String headline() {
        return super.headline();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(boolean) arg) {
            limitAlert = false;
        }
    }

}
