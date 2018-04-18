/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterMadeMealsController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class MealBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishRepo = PersistenceContext.repositories().dishes();
        final Dish dish1 = dishRepo.findByName(Designation.valueOf("tofu grelhado")).get();
        final Dish dish2 = dishRepo.findByName(Designation.valueOf("picanha")).get();
        
        register(dish1, MealType.LUNCH, DateTime.parseDate("06-05-2018"));
        register(dish2, MealType.DINNER, DateTime.parseDate("06-05-2018"));
        
        return true;
    }
    
    private void register(Dish dish, MealType mealType, Calendar cal) {
        final RegisterMadeMealsController controller = new RegisterMadeMealsController();
        try {
            controller.registerMeal(dish, mealType, cal);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
