/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author João Rocha 1161838
 */
public interface BookingRepository extends DataRepository<Booking, Long>{

    public Booking saveBooking(Booking entity) throws DataConcurrencyException, 
            DataIntegrityViolationException;
    
    public Long countReservedMealsByDishType(Calendar cal, DishType dishType, MealType mealType);
}
