package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterMadeMealsController;
import eapli.ecafeteria.domain.execution.Execution;
import eapli.ecafeteria.domain.execution.MadeMeals;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author MFerreira
 */
public class RegisterMadeMealsUI extends AbstractUI {

    private RegisterMadeMealsController controller = new RegisterMadeMealsController();

    @Override
    protected boolean doShow() {
        System.out.println("--------------------------");
        System.out.println("Welcome, please insert the criteria:\n");

        String answer = "";
        do {
            Calendar date = Console.readCalendar("Insert date (DD-MM-YYYY):");

            System.out.println("--------------------------");
            int n;
            do {
                System.out.println("Choose the Meal Type:\n");
                System.out.println("1-Lunch");
                System.out.println("2-Dinner\n");
                n = Console.readInteger("Choose:");
            } while (n != 1 && n != 2);

            MealType mealType = null;
            if (n == 1) {
                mealType = MealType.LUNCH;
            } else if (n == 2) {
                mealType = MealType.DINNER;
            }
            List<Meal> meals = controller.getMealsList(date, mealType);
            if (meals.isEmpty()) {
                System.out.println("There are no meals with that criteria.");
            } else {
                System.out.println("\nThere are " + meals.size() + " meals with that criteria.");
                Console.readLine("Press enter to continue.");

                for (int i = 0; i < meals.size(); i++) {
                    System.out.println("--------------------------");
                    System.out.println((i + 1) + "- " + meals.get(i).toString());
                   System.out.println("--------------------------");

                    Execution exec;
                    try {
                        exec = controller.checkIfExists(meals.get(i));
                        System.out.println("You are going to edit an existing Execution.");

                        MadeMeals madeMeals = numberOfMadeMeals();

                        exec.changeMadeMeals(madeMeals);

                        saveExecution(exec);

                    } catch (NoResultException e) {

                        MadeMeals madeMeals = numberOfMadeMeals();

                        exec = controller.createExecution(meals.get(i), madeMeals);

                        saveExecution(exec);
                    }
                }
            }
            do{
                answer = Console.readLine("\nDo you want to edit more meals? (y/n)");
            } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
            
        } while (answer.equalsIgnoreCase("y"));

        return true;
    }

    private MadeMeals numberOfMadeMeals() {
        int numMadeMeals;
        do {
            numMadeMeals = Console.readInteger("How many Made Meals for this Meal?");
        } while (numMadeMeals < 0);

        return new MadeMeals(numMadeMeals);
    }

    private void saveExecution(Execution exec) {

        try {
            controller.addExecution(exec);
            System.out.println("Execution added!");
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Unable to add this Execution");
        }
    }

    @Override
    public String headline() {
        return "REGISTER THE NUMBER OF MADE MEALS";
    }

}