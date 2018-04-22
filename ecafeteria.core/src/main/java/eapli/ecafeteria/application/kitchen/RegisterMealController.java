/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.*;
import eapli.ecafeteria.domain.authz.*;
import eapli.ecafeteria.domain.dishes.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.domain.menu.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.persistence.*;
import java.util.*;

/**
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
