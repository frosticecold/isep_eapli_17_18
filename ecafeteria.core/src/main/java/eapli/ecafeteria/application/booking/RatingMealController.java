/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingMealController {

    /**
     * Factory
     */
    RepositoryFactory factory;
    /**
     * Cafeteria user
     */
    private CafeteriaUser user = null;
    private Booking selectedBooking = null;
    BookingReportingRepository bookingRepository = PersistenceContext.repositories().bookingReporting();
    private RatingRepository ratingRepository = PersistenceContext.repositories().rating();

    /**
     * List with all bookings of the user
     */
    private List<Booking> bookings = null;

    public RatingMealController() {
        factory = PersistenceContext.repositories();
        user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();
        
        
        ratingRepository = factory.rating();
        bookingRepository = factory.bookingReporting();
        BookingState served = new BookingState();
        served.changeToServed();
        bookings = bookingRepository.findBookingsByCafeteriaUser(user, served);
    }

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
    public Rating addRating(Booking booking, int rating, String comment) 
            throws DataConcurrencyException, DataIntegrityViolationException {
        
        System.out.println("LLL- " + user.user().id().toString());
        Rating rateMeal = new Rating(user, booking, rating, comment);
        rateMeal = ratingRepository.saveRating(rateMeal);
        return rateMeal;
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
