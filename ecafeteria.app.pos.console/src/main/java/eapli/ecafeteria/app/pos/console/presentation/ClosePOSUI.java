/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.ClosePOSController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Oliveira
 */
public class ClosePOSUI extends AbstractUI {

    private final ClosePOSController controller = new ClosePOSController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        controller.closeSession();
        Iterable<DishesPerDishType> dishes = controller.listDeliveredMeals();
        for (DishesPerDishType dish : dishes) {
            System.out.println(dish.dishType + ":" + dish.quantityOfDishes);
        }
        new LoginAction(ActionRight.SALE).execute();
        return true;
    }

    @Override
    public String headline() {
        return "Delivery Report";
    }
}
