/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meal.*;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author MFerreira
 */
public interface MealRepository extends DataRepository<Meal, Long>{
    
    List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType);
    
    Optional<Meal> findMealByDishID(Designation id);
    
}
