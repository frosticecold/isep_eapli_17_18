package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class BookedMealsReportingController implements Controller {


    public BookedMealsReportingController() {

    }

  
    /**
     * Gets the booked meals relation by dish type
     */
    public String getBookedMeals() {

        String msg = "================== Booked Meals ======================================\n";

        Iterable<Booking> bookedMeals = PrevisionsService.prepareBookedMealsList();

        msg += "\n" + bookedMeals.toString() + "\n";

        msg += "Total of Booked Meals : " + PrevisionsService.numberBookedMeals().toString() + "\n";

        msg += "=================================================================================";

        return msg;
    }
}
