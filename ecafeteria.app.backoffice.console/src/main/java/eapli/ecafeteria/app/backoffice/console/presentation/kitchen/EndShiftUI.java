package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.EndShiftController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author JoaoMagalhaes
 */
public class EndShiftUI extends AbstractUI{

    private final EndShiftController theController = new EndShiftController();
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        theController.presentMealsMadeNotSold();
        theController.closeSessions();
        return true;
    }

    @Override
    public String headline() {
        return "ENDSHIFT";
    }
    
}
