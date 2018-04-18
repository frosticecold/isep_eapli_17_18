/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MFerreira
 */
public class RegisterMadeMealsController implements Controller {
    
    private final MealRepository repository = PersistenceContext.repositories().meals();
    
    public List<Meal> getMealsList(Calendar date, MealType mealType){
        return repository.listOfMealsByDateAndMealType(date, mealType);
    }
    
    public Meal registerMeal(final Dish dish, final MealType mealType, final Calendar cal) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final Meal newMeal = new Meal(dish, mealType, cal);

        return this.repository.save(newMeal);
    }
    
    public boolean createExecution(Meal meal, Integer MadeMeals){
        
        return true;
    }
}
