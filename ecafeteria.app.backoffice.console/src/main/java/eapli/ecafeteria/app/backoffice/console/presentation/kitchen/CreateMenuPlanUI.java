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
import javax.persistence.NoResultException;

/**
 *
 * @author pedro
 */
public class CreateMenuPlanUI extends AbstractUI {

    private CreateMenuPlanController controller = new CreateMenuPlanController();

    @Override
    protected boolean doShow() {

        int n;
        MenuPlan mp = null;

        do {
            System.out.println("Choose an option:");

            System.out.println("1-Create plan");
            System.out.println("2-Edit plan");
            System.out.println("0-Exit");

            n = Console.readInteger("Choose:");
        } while (n != 1 && n != 2 && n != 0);

        if (n == 1) {

            Menu menu = controller.getCurrentMenu();
              MenuPlan mplan=null;
            try {
                
                mplan = controller.getMenuPlanFromMenu(menu);
                    System.out.println("Ja existe plano para esse menu");
            } catch (NoResultException e) {
           
                    List<MenuPlanItem> list = new ArrayList<>();

                    Iterable<Calendar> listDays = menu.getWorkWeekDaysIterable();
                    for (Calendar bDay : listDays) {

                        Iterable<Meal> meals = controller.mealsFromMenuByDay(bDay, menu);

                        for (Meal currentMeal : meals) {

                            int quantity = Console.readInteger("Insert the quantity of dishes for meal:" + currentMeal.dish());

                            Quantity q = controller.insertQuantity(quantity);

                            MenuPlanItem mpi = controller.createMenuPlanItem(currentMeal, q);

                            list.add(mpi);

                        }

                    }

                    mp = controller.createMenuPlan(list, menu);

                    System.out.println("numero de menuPLanItem:" + mp.getMenuPlanItemList().size());
                    try {

                        controller.saveMenuPlan(mp, mp.getMenuPlanItemList());

                        System.out.println("GUARDOU O MENUPLAN");

                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        System.out.println("Unable to add this Execution");
                    }
              
            }

        } else if (n == 2) {

            MenuPlan mplan=controller.getActiveMenuPlan();
            
            List<MenuPlanItem>list=mplan.getMenuPlanItemList();
            
            for (MenuPlanItem mPlanItem:list) {
                int quantity = Console.readInteger("Insert the quantity of dishes for meal:"+mPlanItem.getCurrentMeal().dish());
                mPlanItem.getQuantityNumber().setQuantity(quantity);
                
            }
            
            try {
                
                controller.saveMenuPlan(mplan,list);
                
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Unable to add this Execution");
                }
        } else {
            System.exit(0);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Create a plan for this week menu.";
    }

}
