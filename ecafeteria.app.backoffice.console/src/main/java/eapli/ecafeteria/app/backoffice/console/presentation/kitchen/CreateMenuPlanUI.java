/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
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
 * @author pedro bosta
 */
public class CreateMenuPlanUI extends AlertUI {

    private CreateMenuPlanController controller = new CreateMenuPlanController();

    @Override
    protected boolean doShow() {

        int n;

        do {
            System.out.println("Choose an option:");

            System.out.println("1-Create plan");
            System.out.println("2-Edit plan");

            n = Console.readInteger("Choose:");
        } while (n != 1 && n != 2);

        if (n == 1) {

            List<Menu> menus = controller.getCurrentMenus();

            if (menus.isEmpty()) {
                System.out.println("Nao ha menus nesse criterio");

            } else {

                for (int i = 0; i < menus.size(); i++) {
                    System.out.println((i + 1) + "- " + menus.get(i).toString());
                }

                int x;

                do {
                    x = Console.readInteger("Choose the menu:");
                } while (x < 0 || x > menus.size());

                Menu menu = menus.get(x - 1);

                MenuPlan mplan = null;
                MenuPlan savedMenuPlan = null;
                try {

                    mplan = controller.getMenuPlanFromMenu(menu);
                    System.out.println("Ja existe plano para esse menu");

                } catch (NoResultException e) {

                    mplan = controller.createMenuPlan(menu);

                    try {

                        savedMenuPlan = controller.saveMenuPlan(mplan);

                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        System.out.println("Unable to add this Execution");
                    }

                    Iterable<Calendar> listDays = menu.getWorkWeekDaysIterable();
                    for (Calendar bDay : listDays) {

                        Iterable<Meal> meals = controller.mealsFromMenuByDay(bDay, menu);
                        int i = 0;

                        for (Meal currentMeal : meals) {

                            int quantity = Console.readInteger("Insert the quantity of dishes for meal:" + currentMeal.dish());

                            Quantity q = controller.insertQuantity(quantity);
                            MenuPlanItem mpi = controller.createMenuPlanItem(savedMenuPlan, currentMeal, q);

                            try {

                                controller.saveMenuPlanItem(mpi);

                            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                                Logger.getLogger(CreateMenuPlanUI.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }

                }
            }

        } else {

            List<MenuPlan> mplans = controller.getActiveMenuPlans();

            if (mplans.isEmpty()) {
                System.out.println("Nao e possivel editar nenhu plano ois nenhum esta aberto");
            } else {
//
                for (int i = 0; i < mplans.size(); i++) {
                    System.out.println((i + 1) + "- " + mplans.get(i).toString());
                }

                int y;
                do {
                    y = Console.readInteger("Choose the menu you want to edit:");
                } while (y < 0 || y > mplans.size());

                MenuPlan mplan = mplans.get(y - 1);

                List<MenuPlanItem> list = controller.getMenuPlanItemsFromMenuPlan(mplan);

                for (MenuPlanItem mPlanItem : list) {
                    int quantity = Console.readInteger("Insert the quantity of dishes for meal:" + mPlanItem.getCurrentMeal().dish());
                    controller.editMenuPlan(quantity, mPlanItem);

                    try {

                        controller.saveMenuPlanItem(mPlanItem);

                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        System.out.println("Unable to add this Execution");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return super.headline() + "Create a plan for this week menu.";
    }

}
