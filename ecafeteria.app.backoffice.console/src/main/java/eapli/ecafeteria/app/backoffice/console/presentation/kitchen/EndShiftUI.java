package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.EndShiftController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Calendar;

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
        theController.closeSessions();
        System.out.println("\nSessions All Closed\n");
        
        theController.defineMealTypeAndDate();
        System.out.println("The number of meals made but not sold on " + theController.dia() + "/" + theController.date().get(Calendar.MONTH)+1 + "/" + theController.date().get(Calendar.YEAR) + " at " + theController.mealType().toString() + " is : " + theController.presentMealsMadeNotSold()
 + "\n");

        return true;

    }

    @Override
    public String headline() {
        return "ENDSHIFT";
    }
    
}
