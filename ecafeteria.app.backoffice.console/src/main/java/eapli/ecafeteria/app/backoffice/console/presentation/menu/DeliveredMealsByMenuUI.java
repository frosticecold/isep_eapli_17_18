package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.DeliveredMealsByMenuController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByMenuUI extends AbstractUI {

    private DeliveredMealsByMenuController ctrl;

    public DeliveredMealsByMenuUI() {

        this.ctrl = new DeliveredMealsByMenuController();
    }

    @Override
    protected boolean doShow() {

        Long idMenu = Console.readLong("Insert id of menu");

        if (this.ctrl.checkMenuExistence(idMenu)) {
            System.out.println(this.ctrl.prepareDeliveredMealsByMenuList(idMenu));
        } else {
            System.out.println("Menu doesnt exist\nImpossible to continue ...\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
