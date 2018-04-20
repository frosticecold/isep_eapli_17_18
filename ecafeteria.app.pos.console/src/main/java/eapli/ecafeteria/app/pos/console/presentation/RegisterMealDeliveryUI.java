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

    /** Construtor that shall receive the entity of the open session of a certain POS **/
    public RegisterMealDeliveryUI() {
        
        //create controller
        this.ctrl = new RegisterMealDeliveryController();
    }
    
    public void recordNewMealDelivery() {

        //recordNewMealDelivery(pos, idBooking); //records the new delivery on the delivery registry of this session 
    }
    
    /**
     * headline menu
     * @return 
     */
    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

    @Override
    protected boolean doShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
