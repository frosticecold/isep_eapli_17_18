/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingMealController {

    private BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private RatingRepository ratingRepository = PersistenceContext.repositories().rating();

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
    public Rating addRating(int rating, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        Rating rateMeal = new Rating(rating, comment);
        rateMeal = ratingRepository.save(rateMeal);
        return rateMeal;
    }

    /**
     * Method to find all consumed bookings without rating
     *
     * @return all consumed booking without rating
     */
    public List<Booking> findBookings() {
        return bookingRepository.findConsumedBookingWithoutRating();
    }
}
