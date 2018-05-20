/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class JpaBookingRepository
        extends CafeteriaJpaRepositoryBase<Booking, Long>
        implements BookingRepository {

    public Booking saveBooking(Booking entity) throws DataConcurrencyException,
            DataIntegrityViolationException {

        return save(entity);
    }

    @Override
    public Long countReservedMealsByDishType(Calendar cal, DishType dishType, MealType mealType) {
        final Query q;

        q = entityManager().createQuery("SELECT COUNT(e) FROM Booking e "
                + "WHERE e.meal.mealtype=:mealType "
                + "AND e.meal.date=:date "
                + "AND e.meal.dish.dishType=:dt "
                + "AND e.bookingState.actualBookingState=:bs1",
                Long.class);

        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);
        q.setParameter("dt", dishType);
        q.setParameter("bs1", BookingStates.BOOKED);
        return (Long) q.getSingleResult();
    }

    @Override
    public Long getNumberOfDeliveredMealsByDishTypeByDayAndMealType(final Calendar cal, final MealType mealType, final DishType dishType) {
        final Query q;
        q = entityManager().createQuery("SELECT COUNT(e) FROM Booking e "
                + "WHERE e.meal.mealtype=:mealType "
                + "AND e.meal.date=:date "
                + "AND e.meal.dish.dishType=:dt "
                + "AND e.bookingState.actualBookingState=:bs1 ",
                Long.class);
        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);
        q.setParameter("dt", dishType);
        q.setParameter("bs1", BookingStates.SERVED);

        return (Long) q.getSingleResult();
    }

    @Override
    public Long getNumberOfDeliveredMealsByDayAndMealType(final Calendar cal, final MealType mealType) {
        final Query q;
        q = entityManager().createQuery("SELECT COUNT(e) FROM Booking e "
                + "WHERE e.meal.mealtype=:mealType "
                + "AND e.meal.date=:date "
                + "AND e.bookingState.actualBookingState=:bs1 ",
                Long.class);
        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);
        q.setParameter("bs1", BookingStates.SERVED);

        return (Long) q.getSingleResult();
    }

    /**
     * Find booking by cafeteria user that are in a specific state
     *
     * @param user user of the cafeteria
     * @param bookingState booking state
     * @author David Camelo <1161294@isep.ipp.pt>
     *
     * @return list with bookings
     */
    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user,
            BookingState bookingState) {
        Query query = entityManager().createQuery("SELECT booking "
                + "FROM Booking booking "
                + "WHERE booking.bookingState = :bookingState "
                + "AND booking.cafeteriaUser = :cafeteriaUser");

        query.setParameter("bookingState", bookingState);
        query.setParameter("cafeteriaUser", user);

        return query.getResultList();
    }

    @Override
    public List<Booking> getAllBookingsFromMealThatAreServed(Meal m) {
        BookingState s = new BookingState();
        s.changeToServed();
        Query query = entityManager().createQuery("SELECT booking "
                + "FROM Booking booking "
                + "WHERE booking.bookingState = :b "
                + "AND booking.meal = :m");

        query.setParameter("b", s);
        query.setParameter("m", m);

        return query.getResultList();
    }

    @Override
    public List<Booking> getAllBookingsFromMealThatAreBooked(Meal m) {
        BookingState s = new BookingState(); //BOOKED DEFAULT

        Query query = entityManager().createQuery("SELECT booking "
                + "FROM Booking booking "
                + "WHERE booking.bookingState = :b "
                + "AND booking.meal = :m");

        query.setParameter("b", s);
        query.setParameter("m", m);

        return query.getResultList();
    }

    /**
     * check if exists a booking in a given date and mealtype
     *
     * @param user
     * @param mealType
     * @param calendar
     * @return list bookings found
     */
    @Override
    public List<Booking> findBooking(CafeteriaUser user, MealType mealType, Calendar calendar) {

        Query q = entityManager().
                createQuery("SELECT booking FROM Booking booking, Meal meal "
                        + "WHERE booking.meal.mealtype=:mealType "
                        + "AND booking.cafeteriaUser=:cafeteriaUser "
                        + "AND booking.calendar=:calendar", Booking.class);

        q.setParameter("calendar", calendar, TemporalType.DATE);
        q.setParameter("cafeteriaUser", user);
        q.setParameter("mealType", mealType);

        return q.getResultList();
    }

    /**
     * Returns all bookings with Booked Booking State
     */
    @Override
    public Iterable<Booking> findBookedBookings() {

        BookingState state = new BookingState();

        String query = "SELECT e FROM Booking e WHERE bookingstate = :bs";

        final Query q = entityManager().createQuery(query, Booking.class);

        q.setParameter("bs", state.actualState().toString());

        return q.getResultList();
    }

    /**
     * Counts all bookings with Booked State
     */
    @Override
    public Long countBookedBookings() {

        BookingState state = new BookingState();

        String query = "SELECT COUNT(e) FROM Booking e WHERE bookingstate = :bs";

        final Query q = entityManager().createQuery(query, Long.class);

        q.setParameter("bs", state.actualState().toString());

        return (Long) q.getSingleResult();
    }

    /**
     * Returns a list of all bookings made for a certain meal
     *
     * @param m
     * @return
     */
    @Override
    public List<Booking> findBookingsByMeal(Meal m) {
        String query = "SELECT e FROM Booking b"
                + "WHERE e.meal : meal";

        final Query q = entityManager().createQuery(query, Booking.class);

        q.setParameter("meal", m);

        return q.getResultList();
    }
}
