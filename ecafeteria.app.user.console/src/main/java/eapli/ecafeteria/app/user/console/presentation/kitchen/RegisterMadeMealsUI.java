/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.kitchen.RegisterMadeMealsController;
import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MadeMeals;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MFerreira
 */
public class RegisterMadeMealsUI extends AbstractUI{

    private RegisterMadeMealsController controller = new RegisterMadeMealsController();
    
    @Override
    protected boolean doShow() {
        
        System.out.println("Welcome, please insert the criteria:");
        
        Calendar date = Console.readCalendar("Insert date (DD-MM-YYYY):");
        
        int n;
        do{
            System.out.println("Choose the Meal Type:");
            System.out.println("1-Lunch");
            System.out.println("2-Dinner");
            n = Console.readInteger("Choose:");
        }while(n!=1 || n!=2);
        
        MealType mealType = null;
        if(n==1){
            mealType=MealType.LUNCH;
        }else if(n==2){
            mealType=MealType.DINNER;
        }
        List<Meal> meals = controller.getMealsList(date, mealType);
        if(meals.isEmpty()){
            System.out.println("There is no meals with that criteria.");
        } else{
            System.out.println("List of Meals with that criteria;");
            for (int i = 0; i < meals.size(); i++) {
                System.out.println((i+1) + meals.get(i).toString());
            }
            int x;
            do{
                x = Console.readInteger("Choose the meal:");
            }while(x<0 || x>meals.size());
            
            int numMadeMeals = Console.readInteger("How many Made Meals for this Meal?");
            MadeMeals madeMeals = new MadeMeals(numMadeMeals);
            
            Execution e = controller.createExecution(meals.get(x-1), madeMeals);
            
            try {
                controller.addExecution(e);
                System.out.println("Execution added!");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("Unable to add this Execution");
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Register the Number of Made Meals";
    }
    
}
