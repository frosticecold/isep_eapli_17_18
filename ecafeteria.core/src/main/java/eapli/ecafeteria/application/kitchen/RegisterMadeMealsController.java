/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MFerreira
 */
public class RegisterMadeMealsController {
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    private final ExecutionRepository execRepo = PersistenceContext.repositories().executions();
    
    public List<Meal> getMealsList(Calendar date, MealType mealType){
        return mealRepo.listOfMealsByDateAndMealType(date, mealType);
    }
    
    public Execution createExecution(Meal meal, MadeMeals madeMeals){ 
        return new Execution(meal, madeMeals);
    }
    
    public boolean addExecution(Execution e) throws DataConcurrencyException, DataIntegrityViolationException{
        execRepo.save(e);
        
        return true;
    }
}
