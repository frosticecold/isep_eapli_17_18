/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.menuplan.CreateMenuPlanController;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.currentWeekNumber;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class CreateMenuPlanUI extends AbstractUI {

    private CreateMenuPlanController controller = new CreateMenuPlanController();

    @Override
    protected boolean doShow() {

        int n;
        MenuPlan mp=null;
        
        do {
            System.out.println("Choose an option:");

            System.out.println("1-Create plan");
            System.out.println("2-Edit plan");

            n = Console.readInteger("Choose:");
        } while (n != 1 && n != 2 && n != 0);

        if (n == 1) {

            Menu menu = controller.getCurrentMenuWithoutPlan();

            if (menu != null) {
                System.out.println("O ultimo menu já possui um plano de refeicoes");

            } else {

                List<MenuPlanItem> list = new ArrayList<>();

                Calendar bDay = DateTime.beginningOfWeek(Calendar.YEAR, currentWeekNumber());   //esta mal

                for (int i = 0; i < 7; i++) {

                    Iterable<Meal> meals = controller.mealsFromMenuByDay(bDay, menu);

                    for (Meal currentMeal : meals) {
                        
                       int quantity = Console.readInteger("Insert the quantity of dishes for meal:"+currentMeal.toString());
                       
                       Quantity q=controller.insertQuantity(quantity);
                       controller.createMenuPlanItemList(currentMeal, list, q);
                       
                    }
                    
                    bDay.add(Calendar.DAY_OF_MONTH, 1);
                   
                }
                
                 mp=controller.createMenuPlan(list, menu);

                try {
                    
                    controller.save(mp);
                    
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Unable to add this Execution");
                }

            }

        } else if (n == 2) {

            controller.editMenuPlan(n);

        }
        return true;
    }

    @Override
    public String headline() {
        return "Create a plan for this week menu.";
    }

}
