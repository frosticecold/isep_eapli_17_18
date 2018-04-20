/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class RegisterMealController implements Controller {
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
    public Meal registerMeal(final Dish dish, final MealType mealType, final Calendar cal, final Menu menu) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        final Meal newMeal = new Meal(dish, mealType, cal, menu);

        return this.mealRepo.save(newMeal);
    }
    
}
