/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.application.Controller;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ViewAvailableMealsController implements Controller{
    
    private final DishReportingRepository repo = PersistenceContext.repositories().dishReporting();
    
    public void showDishesPerDishType(){
        Iterable<DishesPerDishType> dishes = repo.dishesPerDishType();
        for (DishesPerDishType dish : dishes) {
            System.out.println(dish.dishType + ":");
            System.out.println(dish.quantityOfDishes);
        }
    }
}
