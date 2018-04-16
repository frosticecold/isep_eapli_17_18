/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meal.Meal;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ListMealsController {
    
    private final ListMealsService service = new ListMealsService();
    
    public Iterable<Meal> allMeals() {
        return this.service.allMeals();
    }
}
