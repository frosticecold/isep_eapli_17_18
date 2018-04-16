/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ListMealsService {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    /**
     * Returns all of the dishes
     * @return 
     */
    public Iterable<Meal> allMeals() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        return this.mealRepository.findAll();
    }

}
