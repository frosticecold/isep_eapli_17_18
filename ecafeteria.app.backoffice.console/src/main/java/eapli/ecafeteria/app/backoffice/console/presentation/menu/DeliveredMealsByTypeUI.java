package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.DeliveredMealsByTypeController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByTypeUI extends AbstractUI {

    private DeliveredMealsByTypeController ctrl;

    public DeliveredMealsByTypeUI() {

        this.ctrl = new DeliveredMealsByTypeController();
    }

    @Override
    protected boolean doShow() {

        System.out.println(this.ctrl.prepareListDeliveredMealByType());

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
