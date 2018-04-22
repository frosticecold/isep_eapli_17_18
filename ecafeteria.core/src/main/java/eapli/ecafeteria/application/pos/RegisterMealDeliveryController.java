package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliverySessionDate;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    private ListAvailableMealsService list; //Listing Services
    
    public RegisterMealDeliveryController() {
        
        this.list = new ListAvailableMealsService();
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
    
    /**
     * Returns all AvaliableMealStatics of this certain session to list on UI
     * @param session
     * @return 
     */
    public AvailableMealsStatistics showCurrentStatics(DeliveryMealSession session) {
        
        MealType sessionType;
        
        if(session.isLunch()) sessionType = MealType.LUNCH;
        else sessionType = MealType.DINNER;
        
        Calendar ca = session.date();

        return new AvailableMealsStatistics(ca, sessionType);
    }
}
