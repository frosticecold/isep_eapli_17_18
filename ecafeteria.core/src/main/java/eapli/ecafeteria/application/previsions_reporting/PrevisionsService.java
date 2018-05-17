package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PrevisionsService implements Controller {

    //Construtor of service
    public PrevisionsService() {

    }

    /**
     * returns a list with all the meals that have booked bookings
     *
     * @return
     */
    public Iterable<Booking> prepareBookedMealsList() {

        Iterable<Booking> bookedMeals = PersistenceContext.repositories().booking().findBookedBookings();

        return bookedMeals;
    }

    /**
     * Returns the count of all booked Meals
     *
     * @return
     */
    public Long numberBookedMeals() {

        return PersistenceContext.repositories().booking().countBookedBookings();
    }

    /**
     * returns a list with all meals associated to a menu plan
     */
    public List<Meal> prepareMealsByMenuPlanList() {

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

}
