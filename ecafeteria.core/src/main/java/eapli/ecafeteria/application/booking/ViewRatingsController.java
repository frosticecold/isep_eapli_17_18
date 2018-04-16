/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsController implements Controller {
    
    BookingReportingRepository bookingRepo = PersistenceContext.repositories().bookingReporting();
    
    private Meal meal;
    
    /**
     * Returns a list of all of the bookings that have been served or served but not eaten.
     * @return 
     */
    public Iterable<Booking> listServedBookings() {
        return bookingRepo.listServedBookings();
    }
    
    /**
     * Selects a meal from a list of bookings given an ID
     * @param id
     * @param meal 
     */
    public void setMeal(Long id) {
        for (Booking booking : listServedBookings()) {
            if (booking.getIdBooking() == id) {
                this.meal = booking.getMeal();
            }
        }
    }
    
    /**
     * After the selection of a meal, returns a list with all of its ratings.
     * @param meal
     * @return 
     */
    public Iterable<Rating> ratingsFromMeal() {
        return meal.ratings();
    }
    
    
    
}
