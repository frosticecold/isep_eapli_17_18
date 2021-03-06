/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author João Rocha 1161838
 */
public interface BookingRepository extends DataRepository<Booking, Long>{

    public Booking saveBooking(Booking entity) throws DataConcurrencyException, 
            DataIntegrityViolationException;
    
    public Long countReservedMealsByDishType(Calendar cal, DishType dishType, MealType mealType);
    
    public Long getNumberOfDeliveredMealsByDishTypeByDayAndMealType(final Calendar cal, final MealType mealType, final DishType dishType);

    public Long getNumberOfDeliveredMealsByDayAndMealType(Calendar cal, MealType mealtype);
    
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState bookingState);
    
    public List<Booking> getAllBookingsFromMealThatAreServed(Meal m);
    
    public List<Booking> getAllBookingsFromMealThatAreBooked(Meal m);
    
    public List<Booking> findBooking(CafeteriaUser user, MealType mealType, Calendar date);
    
    public Iterable<Booking> findBookedBookings();
    
    public Long countBookedBookings();
    
    public List<Booking> findBookingsByMeal(Meal m);
}
