/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author Rui Almeida <1160818>
 */
public final class ViewRatingsController implements Controller {

    BookingReportingRepository bookingRepo = PersistenceContext.repositories().bookingReporting();

    /**
     * List of relevant bookings
     */
    private List<Booking> bookings;
    /**
     * Meal to check the rating of
     */
    private Meal meal;

    public ViewRatingsController() {
        readBookings();
    }

    /**
     * Loads all the bookings that have been served or served but not
     * eaten/cancelled in time
     *
     */
    public void readBookings() {
        /*
        Adds bookings that have been served
         */
        for (Booking booking : bookingRepo.findBookingByState(BookingState.BookingStates.SERVED)) {
            bookings.add(booking);
        }
        /*
        Adds bookings that were not served but reserved and not cancelled
         */
        for (Booking booking : bookingRepo.findBookingByState(BookingState.BookingStates.NOT_SERVED)) {
            bookings.add(booking);
        }
    }

    /**
     * Selects a meal from a list of bookings given an ID
     *
     * @param id
     */
    public void setMeal(Long id) {
        for (Booking booking : bookings) {
            if (id.compareTo(booking.getIdBooking()) == 0) {
                this.meal = booking.getMeal();
            }
        }
    }

    /**
     * After the selection of a meal, returns a list with all of its ratings.
     *
     * @param meal
     * @return
     */
    public Iterable<Rating> ratingsFromMeal() {
        return meal.ratings();
    }
    
    /**
     * Returns the read bookings
     * @return 
     */
    public Iterable<Booking> bookings() {
        return this.bookings;
    }

}
