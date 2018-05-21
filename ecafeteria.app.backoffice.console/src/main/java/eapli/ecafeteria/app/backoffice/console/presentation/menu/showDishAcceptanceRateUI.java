package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.showDishAcceptanceRateController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class showDishAcceptanceRateUI extends AbstractUI {

    private showDishAcceptanceRateController ctrl;

    public showDishAcceptanceRateUI() {

        this.ctrl = new showDishAcceptanceRateController();
    }

    @Override
    protected boolean doShow() {
        System.out.println(this.ctrl.prepareInformation());
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
