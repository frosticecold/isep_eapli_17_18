/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.menuplan.CloseMenuPlanController;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

public class CloseMenuPlanUI extends AbstractUI {

    private CloseMenuPlanController controller = new CloseMenuPlanController();

    @Override
    protected boolean doShow() {
        boolean validar;

        List<MenuPlan> lmp = controller.getMenuPlans();

        if (lmp.isEmpty()) {
            System.out.println("There are no open menuPlans");
        } else {

            for (int i = 0; i < lmp.size(); i++) {
                System.out.println((i + 1) + "- " + lmp.get(i).toString());
            }

            int y;
            do {
                y = Console.readInteger("Choose the menuPlan you want to close:");
            } while (y < 0 || y > lmp.size());

            MenuPlan mp = lmp.get(y - 1);

            validar = controller.validate(mp);
            System.out.println("ISTOOOOOOO->->->" + validar);
            if (validar == false) {
                System.out.println("It was not possible to close the menu plan.");
            } else {
                
                    controller.changeStatus(mp);
                try {
                    
                    controller.saveMenuPlan(mp);

                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(CloseMenuPlanUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(CloseMenuPlanUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("The menu plan was closed successfully. ");
            }

        }
        return true;
    }

    @Override
    public String headline() {
        return "Close the menu plan for this week's menu.";
    }

}
