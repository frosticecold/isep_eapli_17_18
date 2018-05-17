package eapli.ecafeteria.application.prediction_reporting;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PredictionService implements Controller {

    //Construtor of service
    public PredictionService() {

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

}
