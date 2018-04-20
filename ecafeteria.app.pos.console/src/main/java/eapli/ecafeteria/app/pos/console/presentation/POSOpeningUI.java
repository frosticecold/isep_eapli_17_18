package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.POSOpeningController;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.POS;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Currency;

/**
 *
 * @author Class 2DC
 */
public class POSOpeningUI extends AbstractUI {

    private final POSOpeningController theController = new POSOpeningController();

    public POSOpeningUI() {

    }

    @Override
    protected boolean doShow() {
        System.out.println(String.format("Creating Delivery Meal Session...\n", (Object) null));
        if(theController.checkPoSState()){
            theController.createDeliveryMealSession();
            return true;
        }
        System.out.println(String.format("Couldn't open point of sale as it was already opened."));
        return false;
    }

    @Override
    public String headline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
