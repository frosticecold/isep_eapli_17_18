/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author MFerreira
 */
public interface MealRepository extends DataRepository<Meal, Long> {

    List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType);

    Optional<Meal> findMealByDishID(Designation id);

    Optional<Meal> findOne(Long id);

    Iterable<Meal> listMealsFromMenuByGivenDay(final Menu menu, final Calendar day);

    List<Meal> findMealsByMenu(Menu menu);

    List<Meal> getMealByDate(Calendar cal);

    List<Meal> getMealByDish(Dish dish);
    
    List<Meal> getMealsByMealType(MealType type);

}
