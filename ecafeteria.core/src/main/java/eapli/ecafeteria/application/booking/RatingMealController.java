/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingMealController {

    BookingReportingRepository bookingRepository = PersistenceContext.repositories().bookingReporting();
    private RatingRepository ratingRepository = PersistenceContext.repositories().rating();

    /**
     * List with all bookings of the user
     */
    private List<Booking> bookings = null;

    /**
     * Method to add a rating on a booking of the meal
     *
     * @param booking
     * @param rating rate of the meal
     * @param comment description about booking of the meal
     * @return rateMeal
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public Rating addRating(Booking booking, int rating, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        Rating rateMeal = new Rating(booking, rating, comment);
        rateMeal = ratingRepository.save(rateMeal);
        return rateMeal;
    }

    /**
     * Method to find all consumed bookings without rating
     *
     * @return all consumed booking without rating
     */
    public List<Booking> findBookings() {
        bookings = new ArrayList<>();
        BookingState served = new BookingState();
        served.changeToServed();

        for (Booking booking : bookingRepository.findBookingByState(served)) {
            bookings.add(booking);
        }
        return bookings;

    }

    /**
     * Shows booking that are in the booked state
     *
     * @return list of bookings
     */
    public List<Booking> showBookings() {
        return bookings;
    }
}
