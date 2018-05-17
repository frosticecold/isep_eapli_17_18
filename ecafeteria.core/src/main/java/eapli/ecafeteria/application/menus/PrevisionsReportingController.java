package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PrevisionsReportingController implements Controller {

    private PrevisionsService service;

    public PrevisionsReportingController() {

    }

    /**
     * Prepare previsions service
     */
    public void prepareService() {

        this.service = new PrevisionsService();
    }

    /**
     * Gets the booked meals relation by dish type
     */
    public String getBookedMeals() {

        String msg = "================== Booked Meals ======================================\n";

        Iterable<Booking> bookedMeals = this.service.prepareBookedMealsList();

        msg += "\n" + bookedMeals.toString() + "\n";

        msg += "=================================================================================";

        return msg;
    }
}
