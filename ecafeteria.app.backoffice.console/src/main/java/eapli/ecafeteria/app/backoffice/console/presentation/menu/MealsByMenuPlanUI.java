package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.MealsByMenuPlanController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class MealsByMenuPlanUI extends AbstractUI {

    private MealsByMenuPlanController ctrl;

    public MealsByMenuPlanUI() {

        this.ctrl = new MealsByMenuPlanController();
    }

    @Override
    protected boolean doShow() {
        System.out.println(this.ctrl.getMealsByMenuPlan());
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
