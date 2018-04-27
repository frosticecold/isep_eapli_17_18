/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.execution.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ViewAvailableMealsController implements Controller {

    private final ListAvailableMealsService availableMealsService = new ListAvailableMealsService();
    private static final int LUNCH_START = 12;
    private static final int LUNCH_END = 16;
    
    public AvailableMealsStatistics findAvailableMealsPerDay() {
        MealType mealType = checkMealType();
        return availableMealsService.calcStatistics(DateTime.now(), mealType);
    }

    public MealType checkMealType() {
        Calendar time = DateTime.now();
        if (time.get(Calendar.HOUR_OF_DAY) >=LUNCH_START && time.get(Calendar.HOUR_OF_DAY) <= LUNCH_END) {
            return MealType.LUNCH;
        }
        return MealType.DINNER;
    }
}
