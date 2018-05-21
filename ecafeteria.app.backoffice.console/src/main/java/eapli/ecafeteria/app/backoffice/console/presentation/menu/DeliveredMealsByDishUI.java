package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.DeliveredMealsByDishController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByDishUI extends AbstractUI {

    private DeliveredMealsByDishController ctrl;

    public DeliveredMealsByDishUI() {

        this.ctrl = new DeliveredMealsByDishController();
    }

    @Override
    protected boolean doShow() {

        String dishName = Console.readLine("Insert the name of the dish\n");

        if (this.ctrl.checkDish(dishName)) {

            System.out.println(this.ctrl.prepareDeliveredMealsByDish(dishName));
        } else {
            System.out.println("Dish does not exist\nImpossible to continue...\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
