/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class BookingBootstrapper implements Action  {

    @Override
    public boolean execute() {
        /*
        Repositories
        */
        final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();
        final MealRepository mealRepo = PersistenceContext.repositories().meals();
        
        /*
        Final variables (Users, states, meals..)
        */
        /*
        Users
        */
        final Optional<CafeteriaUser> user1 = repo.findByMecanographicNumber(new MecanographicNumber("900330"));
        final Optional<CafeteriaUser> user2 = repo.findByMecanographicNumber(new MecanographicNumber("900331"));
        /*
        States
        */
        final BookingState served = new BookingState();
        served.changeToServed();
        final BookingState notServed = new BookingState();
        notServed.changeToNotServed();
        final BookingState cancelled = new BookingState();
        cancelled.changeToCanceled();
        final BookingState reserved = new BookingState();
        /*
        Meals
        */
        final Optional<Meal> meal1 = mealRepo.findOne(new Long(28));
        final Optional<Meal> meal2 = mealRepo.findOne(new Long(29));
        final Optional<Meal> meal3 = mealRepo.findOne(new Long(30));
        final Optional<Meal> meal4 = mealRepo.findOne(new Long(31));
        final Optional<Meal> meal5 = mealRepo.findOne(new Long(31));
        final Optional<Meal> meal6 = mealRepo.findOne(new Long(33));
        final Optional<Meal> meal7 = mealRepo.findOne(new Long(34));
        final Optional<Meal> meal8 = mealRepo.findOne(new Long(35));
        final Optional<Meal> meal9 = mealRepo.findOne(new Long(37));
        
        /*
        Register bookings
        */
        register(served, DateTime.parseDate("06-05-2018"), user1.get().user().username(), meal1.get());
        register(served, DateTime.parseDate("06-05-2018"), user2.get().user().username(), meal2.get());
        register(notServed, DateTime.parseDate("07-05-20188"), user1.get().user().username(), meal3.get());
        register(cancelled, DateTime.parseDate("07-05-2018"), user2.get().user().username(), meal4.get());
        register(served, DateTime.parseDate("07-05-2018"), user1.get().user().username(), meal5.get());
        register(reserved, DateTime.parseDate("07-05-2018"), user1.get().user().username(), meal6.get());
        register(reserved, DateTime.parseDate("07-05-2018"), user2.get().user().username(), meal6.get());
        register(reserved, DateTime.parseDate("07-05-2018"), user1.get().user().username(), meal7.get());
        register(reserved, DateTime.parseDate("07-05-2018"), user2.get().user().username(), meal8.get());
        register(reserved, DateTime.parseDate("07-05-2018"), user1.get().user().username(), meal9.get());
        register(served, DateTime.parseDate("07-05-2018"), user1.get().user().username(), meal6.get());
        
        return true;
    }
    
    /**
     * Tries to register a booking in the database
     * @param state
     * @param date
     * @param user
     * @param meal 
     */
    private void register(BookingState state, Calendar date, Username user, Meal meal) {
        final BookingMealController controller = new BookingMealController();
        try {
            controller.persistBooking(user, date, state, meal);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Could not bootstrap bookings!\n");
            Logger.getLogger(BookingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
