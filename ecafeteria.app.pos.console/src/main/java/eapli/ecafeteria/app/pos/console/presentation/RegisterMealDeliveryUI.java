package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.RegisterMealDeliveryController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryUI extends AbstractUI {
    
    private RegisterMealDeliveryController ctrl;
    private ViewAvailableMealsUI availableMeals = new ViewAvailableMealsUI();

    /** Construtor that shall receive the entity of the open session of a certain POS **/
    public RegisterMealDeliveryUI() {
        
        //create controller
        this.ctrl = new RegisterMealDeliveryController();
    }
    
    public void recordNewMealDelivery() {

        //recordNewMealDelivery(pos, idBooking); //records the new delivery on the delivery registry of this session 
    }

    @Override
    protected boolean doShow() {
        availableMeals.doShow();
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
