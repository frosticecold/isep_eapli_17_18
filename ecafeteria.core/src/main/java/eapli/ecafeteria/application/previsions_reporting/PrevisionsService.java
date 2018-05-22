package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Using the singleton pattern
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
class PrevisionsService implements Controller {
    
    private static PrevisionsService instance = new PrevisionsService();
    

    //Construtor of service
    private PrevisionsService() {
        
    }
    
    
    /**
     * Lets others classes use this service
     * @return 
     */
    public static PrevisionsService use() {
        
        return instance;
    }
    
    /**
     * returns a list with all the meals that have booked bookings
     *
     * @return
     */
    public static Iterable<Booking> prepareBookedMealsList() {

        Iterable<Booking> bookedMeals = PersistenceContext.repositories().booking().findBookedBookings();

        return bookedMeals;
    }

    /**
     * Returns the count of all booked Meals
     *
     * @return
     */
    public static Long numberBookedMeals() {

        return PersistenceContext.repositories().booking().countBookedBookings();
    }

    /**
     * returns a list with all meals associated to a menu plan
     */
    public static List<Meal> prepareMealsByMenuPlanList() {

        List<MenuPlan> menuPlans = PersistenceContext.repositories().menuPlan().getActiveMenuPlans();

        List<Meal> meals;

        List<Meal> allMeals = new ArrayList();

        Menu menu;

        int j;

        for (int i = 0; i < menuPlans.size(); i++) {
            menu = menuPlans.get(i).getSelectedMenu();

            meals = PersistenceContext.repositories().meals().findMealsByMenu(menu);

            for (j = 0; j < meals.size(); j++) {
                allMeals.add(meals.get(j));
            }
        }

        return allMeals;
    }

    /**
     * Returns of all delivered meals by date
     *
     * @return
     */
    public static List<DeliveryRegistry> deliveredMealsByDate(Calendar date) {

        List<DeliveryRegistry> meals = PersistenceContext.repositories().deliveryRegistryRepository().deliveredMealsByDate(date);

        return meals;
    }

    /**
     * Returns a list of delivered meals by a certain meal
     */
    public static List<DeliveryRegistry> deliveredMealsByMeal(Meal meal) {

        List<DeliveryRegistry> list = new ArrayList();

        int j = 0;

        boolean found = false;

        List<Booking> bookingsByMeal = PersistenceContext.repositories().booking().findBookingsByMeal(meal);

        DeliveryRegistry r; //reference

        if (!bookingsByMeal.isEmpty()) {

            for (int i = 0; i < bookingsByMeal.size(); i++) {

                if (!PersistenceContext.repositories().deliveryRegistryRepository().deliveredMealByBooking(bookingsByMeal.get(i)).isEmpty()) {

                    j = 0;

                    found = false;

                    r = PersistenceContext.repositories().deliveryRegistryRepository().deliveredMealByBooking(bookingsByMeal.get(i)).get(0);

                    while (!found && j < list.size()) { //to ensure that each entity is added to the list just once
                        if (list.get(j).id() == r.id()) {
                            found = true;
                        }
                        j++;
                    }

                    if (!found) {
                        list.add(r);
                    }
                }
            }
        }

        return list;
    }

    /**
     * Returns a list of delivered meals by a certain menu
     *
     * @param menu
     * @return
     */
    public static List<DeliveryRegistry> deliveredMealsByMenu(Menu menu) {

        List<DeliveryRegistry> list = new ArrayList();

        List<DeliveryRegistry> listTmp;

        List<Meal> meals = PersistenceContext.repositories().meals().findMealsByMenu(menu);

        for (int m = 0; m < meals.size(); m++) {

            listTmp = PrevisionsService.use().deliveredMealsByMeal(meals.get(m));

            for (int d = 0; d < listTmp.size(); d++) {

                list.add(listTmp.get(d));
            }
        }

        return list;
    }

    /**
     * Returns a list of delivered meals by a certain mealtype
     *
     * @param type
     * @return
     */
    public static List<DeliveryRegistry> deliveredMealsByType(MealType type) {

        List<DeliveryRegistry> list = new ArrayList();

        List<Meal> meals = PersistenceContext.repositories().meals().getMealsByMealType(type);

        List<DeliveryRegistry> listTmp;

        for (int m = 0; m < meals.size(); m++) {

            listTmp = PrevisionsService.use().deliveredMealsByMeal(meals.get(m));

            for (int d = 0; d < listTmp.size(); d++) {

                list.add(listTmp.get(d));
            }
        }

        return list;
    }

    /**
     * Calculates de acceptance rate of the ratings of dishes
     *
     * @return
     */
    public static double dishAcceptanceRate() {

        double rate = 0.0;

        Iterable<Rating> ratings = (List<Rating>) PersistenceContext.repositories().rating().findAll();

        int acceptedDishes = 0;

        int total = 0;

        for (Rating r : ratings) {

            total++;

            if (r.getRating() >= 3) {
                acceptedDishes++;
            }
        }

        if (acceptedDishes == 0 || total == 0) {

            rate = 0;
        } else {
            rate = (double) acceptedDishes / total;
        }

        return rate * 100.0;
    }

    /**
     * Returns all delivered meals of a certain dish
     *
     * @param dish
     * @return
     */
    public static  List<DeliveryRegistry> deliveredMealsByDish(Dish dish) {

        List<DeliveryRegistry> list = new ArrayList();

        List<Meal> meals = PersistenceContext.repositories().meals().getMealsByDish(dish);

        List<DeliveryRegistry> listTmp;

        for (int m = 0; m < meals.size(); m++) {

            listTmp = PrevisionsService.use().deliveredMealsByMeal(meals.get(m));

            for (int l = 0; l < listTmp.size(); l++) {

                list.add(listTmp.get(l));
            }
        }

        return list;
    }
}
