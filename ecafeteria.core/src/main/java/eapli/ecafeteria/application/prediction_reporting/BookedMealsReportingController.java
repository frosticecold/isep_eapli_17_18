package eapli.ecafeteria.application.prediction_reporting;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class BookedMealsReportingController implements Controller {

    private PredictionService service;

    public BookedMealsReportingController() {

    }

    /**
     * Prepare previsions service
     */
    public void prepareService() {

        this.service = new PredictionService();
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
