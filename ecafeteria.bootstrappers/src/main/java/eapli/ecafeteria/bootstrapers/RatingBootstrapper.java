/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class RatingBootstrapper implements Action{

    private final RatingRepository ratingRepository = PersistenceContext.repositories().rating();

    @Override
    public boolean execute() {
        /*
        Repositories
        */
        final BookingReportingRepository bookingRepo = PersistenceContext.repositories().bookingReporting() ;
        final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();
        /*
        Final variables (Users, ratings, comments..)
        */
        /*
        Users
        */
        final Optional<CafeteriaUser> user1 = userRepo.findByMecanographicNumber(new MecanographicNumber("900330"));
        final Optional<CafeteriaUser> user2 = userRepo.findByMecanographicNumber(new MecanographicNumber("900331"));
        /*
        States
        */
        final BookingState served = new BookingState();
        served.changeToServed();
        final BookingState notServed = new BookingState();
        notServed.changeToNotServed();
        final BookingState cancelled = new BookingState();
        cancelled.changeToCanceled();
        /*
        Bookings
        */
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingRepo.findBookingByState(served)) {
            bookings.add(booking);
        }
        for (Booking booking : bookingRepo.findBookingByState(notServed)) {
            bookings.add(booking);
        }
        /*
        Comments
        */
        final String badComment       = "Horrible, didn't like it one bit!";
        final String poorComment      = "It wasn't very well cooked.";
        final String fairComment      = "It was okay, not good, not bad.";
        final String goodComment      = "Nice food, will recommend to friends!";
        final String excellentComment = "Fantastic food! Loved everything about it!";
        /*
        Ratings
        */
        final int BAD       = 1;
        final int POOR      = 2;
        final int FAIR      = 3;
        final int GOOD      = 4;
        final int EXCELLENT = 5;
        
        
        /*
        DATES
        */
        Calendar time1      = DateTime.parseDate("07-05-2018");
        Calendar time2      = DateTime.parseDate("14-05-2018");
        Calendar time3      = DateTime.parseDate("21-05-2018");
        Calendar time4      = DateTime.parseDate("28-06-2018");
        /*
        Register Ratings
        */
        Rating rating;
        for (int i = 0; i < bookings.size(); i++) {
            if (i == 0)  { rating = new Rating(user1.get(), bookings.get(i), BAD, badComment, time4);             register(rating); }
            if (i == 1)  { rating = new Rating(user2.get(), bookings.get(i), POOR, poorComment, time1);           register(rating); }
            if (i == 2)  { rating = new Rating(user1.get(), bookings.get(i), FAIR, fairComment, time2);           register(rating); }
            if (i == 3)  { rating = new Rating(user2.get(), bookings.get(i), GOOD, goodComment, time3);           register(rating); }
            if (i >= 4)  { rating = new Rating(user1.get(), bookings.get(i), EXCELLENT, excellentComment, time4); register(rating); }
        }
        
        return true;
    }
    
    /**
     * Tries to register a rating in the database
     * @param state
     * @param date
     * @param user
     * @param meal 
     */
    private void register(Rating rating) {
        try {
            ratingRepository.save(rating);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Could not save ratings to database! Error in boostrap!\n");
            Logger.getLogger(RatingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
