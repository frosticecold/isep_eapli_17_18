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
    
    /**
     * 
     * @param mecNumber - MecanographicNumber of the client
     * @param booking - id of the booking to register
     */
    public void recordNewMealDelivery(String mecNumber, long booking) {

        //verifies if user is viable or active
        if(!this.ctrl.validateClient(mecNumber)) {
            System.out.println("User doesnt exists!MecanographicNumber doesnt exist!");
        }
        else if(!this.ctrl.validatesBooking(booking)) System.out.println("This booking");
            
        
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
