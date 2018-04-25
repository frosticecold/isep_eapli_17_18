package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.RegisterMealDeliveryController;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryUI extends AbstractUI {
    
    private final RegisterMealDeliveryController ctrl;
    private final DeliveryMealSession session;

    /** Construtor that shall receive the entity of the open session of a certain POS
     * @param session **/
    public RegisterMealDeliveryUI(DeliveryMealSession session) {
         this.session = session;
        //create controller
        this.ctrl = new RegisterMealDeliveryController(session); 
    }
    
    public void recordNewMealDelivery(String idClient, long booking) {

        //this.ctrl.registerNewMealDelivery(idClient, booking);
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
