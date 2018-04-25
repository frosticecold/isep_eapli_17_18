/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
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
                + "AND (e.bookingState.actualBookingState=:bs1 OR e.bookingState.actualBookingState=:bs2)",
                 Long.class);

        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);
        q.setParameter("dt", dishType);
        q.setParameter("bs1", BookingStates.BOOKED);
        q.setParameter("bs2", BookingStates.SERVED);
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
}
