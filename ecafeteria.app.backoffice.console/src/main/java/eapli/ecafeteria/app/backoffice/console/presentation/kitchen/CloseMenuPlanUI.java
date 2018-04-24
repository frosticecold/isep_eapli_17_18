/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.menuplan.CloseMenuPlanController;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.framework.presentation.console.AbstractUI;
import javax.persistence.NoResultException;

public class CloseMenuPlanUI extends AbstractUI {

    private CloseMenuPlanController controller = new CloseMenuPlanController();

    @Override
    protected boolean doShow() {
        boolean validar;
        try {
            MenuPlan mp = controller.getMenuPlan();
            validar = controller.validate(mp);
            if (validar == false) {
                System.out.println("It was not possible to close the menu plan.");
            } else {
                System.out.println("The menu plan was closed successfully. ");
            }
        } catch (NoResultException e) {
            System.out.println("There are no open menu plans.");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Close the menu plan for this week's menu.";
    }

}
