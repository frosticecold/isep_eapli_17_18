package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.DeliveredMealsByMealController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author PedroEmanuelCoelho 113148@isep.ipp.pt
 */
public class DeliveredMealsByMealUI extends AbstractUI {

    private DeliveredMealsByMealController ctrl;

    public DeliveredMealsByMealUI() {

        this.ctrl = new DeliveredMealsByMealController();
    }

    @Override
    protected boolean doShow() {

        Long m = Console.readLong("Insert meals id");

        if (this.ctrl.checkMealExistence(m)) {

            System.out.println(this.ctrl.prepareListDeliveredMealsByMeal(m));

        } else {
            System.out.println("Meal doesnt exist\n Impossible to continue ...\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
