/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meal.*;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MFerreira
 */
public interface MealRepository extends DataRepository<Meal, Long>{
    
    List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType);
    
}
