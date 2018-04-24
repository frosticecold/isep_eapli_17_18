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

    private int rate;
    private String comment;

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
     * @param booking
     * @param rating rate of the meal
     * @param comment description about booking of the meal
     * @return rateMeal
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public Rating addRating(Booking booking, String comment)
            throws DataConcurrencyException, DataIntegrityViolationException {
        if (booking == null || comment == null) {
            System.out.println("Invalid. Please check.");
            //throw new IllegalArgumentException("Invalid. Please check.");
        }
        // System.out.println("Username: " + user.user().id().toString());
//        try {
//            addRating(null, 0, null);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid!");
//        }
        Rating rateMeal = new Rating(booking, rate, comment);
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

    public boolean readRating(int rating) {
        if (rating < 0 || rating > 5) {
            System.out.println("Rating Invalid. Check!");
            return false;
        }
        this.rate = rating;
        return true;
    }

    public boolean readComment() {
        String answer = "";
        do {
            answer = Console.readLine("Do you wish to leave a comment? (yes) or (no)");
        } while (!answer.equals("yes") && !answer.equals("no"));

        return !answer.equals("no");
    }
}
