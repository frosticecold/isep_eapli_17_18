/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.application.pos.ClosePOSController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.DateTime;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author André Oliveira 1040862
 */
public class ClosePOSUI extends AbstractUI {

    private final ClosePOSController controller = new ClosePOSController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        controller.closeSession();
        Map<DishType, Long> map = controller.listDeliveredMeals(DateTime.now(), controller.checkMealype());

        String output = "";
        for (Entry<DishType, Long> e : map.entrySet()) {
            output += e.getKey() + " : " + e.getValue() + "\n";
        }
        System.out.println(output);
        new LoginAction(ActionRight.SALE).execute();
        return true;
    }

    @Override
    public String headline() {
        return "Delivery Report";
    }
}
