/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.execution.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ViewAvailableMealsController implements Controller {

    private final ListAvailableMealsService availableMealsService = new ListAvailableMealsService();
    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();

    public AvailableMealsStatistics findAvailableMealsPerDay(Calendar cal, MealType mealtype) {
        return availableMealsService.calcStatistics(cal, mealtype);
    }

    public void showNotDeliveredBookedMeals() {
        
    }
}
