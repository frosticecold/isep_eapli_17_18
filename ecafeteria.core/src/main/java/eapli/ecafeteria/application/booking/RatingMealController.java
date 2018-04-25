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
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.List;

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

    /**
     * Booking Reporting Repository
     */
    private BookingReportingRepository bookingRepository = PersistenceContext.repositories().bookingReporting();

    /**
     * Rating repository
     */
    private RatingRepository ratingRepository = PersistenceContext.repositories().rating();

    /**
     * List with all bookings of the user
     */
    private List<Booking> bookings = null;
    /**
     * Rating
     */
    private int rating;
    private Meal meal;

    /**
     * Constructor
     */
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
     * @param meal
     * @param booking
     * @param comment description about booking of the meal
     * @return rateMeal
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public Rating addRating(Meal meal, Booking booking, String comment)
            throws DataConcurrencyException, DataIntegrityViolationException {
        if (booking == null || comment == null) {
            System.out.println("Invalid. Please check.");
        }
        Rating rateMeal = new Rating(meal, booking, rating, comment);
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

    /**
     * Method for check if rating is valid
     *
     * @param rating
     * @return
     */
    public boolean readRating(int rating) {
        if (rating < 0 || rating > 5) {
            System.out.println("Rating Invalid. Check!");
            return false;
        }
        this.rating = rating;
        return true;
    }

    /**
     * Method for accept if wish leave a comment or not
     *
     * @return
     */
    public boolean readComment() {
        String answer = "";
        do {
            answer = Console.readLine("Do you wish to leave a comment? (yes) or (no)");
        } while (!answer.equals("yes") && !answer.equals("no"));

        return !answer.equals("no");
    }
}
