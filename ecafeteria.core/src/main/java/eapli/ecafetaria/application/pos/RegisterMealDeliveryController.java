package eapli.ecafetaria.application.pos;

import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    public RegisterMealDeliveryController() {
        
        
    }
    
    /**
     * Method that will focus on regiter a meal delivery
     * given by in the delivery Registry of the pos session given by the UI
     * @param idUser User which will consume the meal
     * @param idBooking Booking which will be delivered
     * @return 
     */    
    public boolean registerNewMealDelivery(long idUser, long idBooking) {
        
        //code to register new delivery on DeliveryMealSession
          
        //code to fetch the BookingsRepository on the PersistenceContext
        
        //changeState(idBooking, bookingsRepo); //will change the state of the booking delivered
        
        return true;
    }
}
