/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.reporting.meals;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealReportingRepository;
import eapli.framework.application.Controller;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class MealReportingController implements Controller {

    private final MealReportingRepository repo = PersistenceContext.repositories().mealReporting();
    
    /**
     * Reports all the meals that have been served/cooked and were available to receive rating
     * @return 
     */
    public Iterable<Meal> listServedMeals() {
        return repo.listServedMeals();
    }


}
