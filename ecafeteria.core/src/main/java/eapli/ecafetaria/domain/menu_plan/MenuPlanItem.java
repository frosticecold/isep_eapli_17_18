/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu_plan;

import eapli.ecafeteria.domain.meal.Meal;

/**
 *
 * @author pedro
 */
public class MenuPlanItem {
    
    private Meal currentMeal;
    
    private int quantity;

    public MenuPlanItem(Meal currentMeal, int quantity) {
        this.currentMeal = currentMeal;
        this.quantity = quantity;
    }
    
    
}
