/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MFerreira
 */
public class RegisterMadeMealsController {
    
    private final MealRepository repository = PersistenceContext.repositories().meals();
    
    public List<Meal> getMealsList(Calendar date, MealType mealType){
        return repository.listOfMealsByDateAndMealType(date, mealType);
    }
    
    public boolean createExecution(Meal meal, Integer MadeMeals){
        
        return true;
    }
}
