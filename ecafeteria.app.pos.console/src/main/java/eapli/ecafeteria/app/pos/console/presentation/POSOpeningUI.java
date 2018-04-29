package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.POSOpeningController;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Class 2DC
 */
public class POSOpeningUI extends AbstractUI {

    private POSOpeningController theController;

    public POSOpeningUI() {
        this.theController = new POSOpeningController();
    }

    @Override
    protected boolean doShow() {
        if (!theController.checkPoSState()) {
            System.out.println(String.format("Creating Delivery Meal Session...\n", (Object) null));
            theController.createDeliveryMealSession();
            return true;
        }
        System.out.println(String.format("Couldn't open point of sale as it was already opened."));
        return false;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
