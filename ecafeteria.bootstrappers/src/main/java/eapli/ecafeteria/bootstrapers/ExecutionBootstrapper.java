/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterMadeMealsController;
import eapli.ecafeteria.domain.execution.Execution;
import eapli.ecafeteria.domain.execution.MadeMeals;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ExecutionBootstrapper implements Action {

    @Override
    public boolean execute() {

        MealRepository mealRepo = PersistenceContext.repositories().meals();

        //Lunch
        Meal meal1 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.LUNCH).get(0);
        Meal meal2 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.LUNCH).get(1);
        Meal meal3 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.LUNCH).get(2);

        //Dinner
        Meal meal4 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.DINNER).get(0);
        Meal meal5 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.DINNER).get(1);
        Meal meal6 = mealRepo.listOfMealsByDateAndMealType(DateTime.parseDate("07-05-2018"), MealType.DINNER).get(2);

        MadeMeals quantity1 = new MadeMeals(70);
        MadeMeals quantity2 = new MadeMeals(40);
        MadeMeals quantity3 = new MadeMeals(50);
        MadeMeals quantity4 = new MadeMeals(70);
        MadeMeals quantity5 = new MadeMeals(40);
        MadeMeals quantity6 = new MadeMeals(50);

        Execution execution1 = new Execution(meal1, quantity1);
        Execution execution2 = new Execution(meal2, quantity2);
        Execution execution3 = new Execution(meal3, quantity3);
        Execution execution4 = new Execution(meal4, quantity4);
        Execution execution5 = new Execution(meal5, quantity5);
        Execution execution6 = new Execution(meal6, quantity6);

        register(execution1);
        register(execution2);
        register(execution3);
        register(execution4);
        register(execution5);
        register(execution6);

        return true;
    }

    private void register(Execution exec) {
        final RegisterMadeMealsController controller = new RegisterMadeMealsController();

        try {
            controller.addExecution(exec);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Could not bootstrap executions!\n");
            Logger.getLogger(BookingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
