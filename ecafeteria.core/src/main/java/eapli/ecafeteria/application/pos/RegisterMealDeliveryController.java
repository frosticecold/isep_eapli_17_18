package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    private ListingService list; //Listing Services
    
    public RegisterMealDeliveryController() {
        
        this.list = new ListingService();
    }
    
    /**
     * Method that will focus on regiter a meal delivery
     * given by in the delivery Registry of the pos session given by the UI
     * @param idClient
     * @param idBooking Booking which will be delivered
     * @return 
     */    
    public boolean registerNewMealDelivery(long idClient, long idBooking) {
        
        //code to register new delivery on DeliveryMealSession
          
        //code to fetch the BookingsRepository on the PersistenceContext
        
        //changeState(idBooking, bookingsRepo); //will change the state of the booking delivered
        
        return true;
    }
}
