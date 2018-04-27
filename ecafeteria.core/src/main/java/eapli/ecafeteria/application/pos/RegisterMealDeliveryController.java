package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    private DeliveryMealSession session;
    private SystemUser cashier;
    
    public RegisterMealDeliveryController() {
        this.cashier = AuthorizationService.session().authenticatedUser();
    }
    
    /**
     * Method that will focus on recording a new meal delivery on persistence
     * saving the booking which will be delivered, the client who made the booking and the POS where it was delivered
     * @param idClient
     * @param idBooking Booking which will be delivered
     */    
    public void registerNewMealDelivery(String number, long idBooking)  {
        useSession();

        //obtain the booking
        Booking booking = PersistenceContext.repositories().booking().findOne(idBooking).get();
        
        //transform string into MecanographicNumber
            
        MecanographicNumber mNumber = new MecanographicNumber(number);

        //obtain the client
        
        CafeteriaUser client = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(mNumber).get();
                
        //add new record of the delivery just made on DeliveryRegistry
        
        DeliveryRegistry registry = new DeliveryRegistry(session, client, booking);
        
        //persist this Registry
        try{
            PersistenceContext.repositories().deliveryRegistryRepository().save(registry);
        }
        catch (Exception e) {
            
        }
        //change state of the booking just recorded - to served
        
        PersistenceContext.repositories().booking().findOne(idBooking).get().getBookingState().changeToServed();     
    }
   
    /**
     * Validates booking by searching on the repository
     * @param idBooking
     * @return 
     */
    public boolean validatesBooking(long idBooking) {
        
        boolean flag = false;
        
        //get booking by id
        
        if(PersistenceContext.repositories().booking().findOne(idBooking).isPresent()) flag = true;
        
        return flag;
    }
    
    /**
     * Validate if user exists
     * @param number
     * @return 
     */
    public boolean validateClient(String number)  {
        
        boolean flag = false;
        
        MecanographicNumber mNumber = new MecanographicNumber(number);
        
        if(PersistenceContext.repositories().cafeteriaUsers().findOne(mNumber).isPresent()) flag = true;
        
        return flag;
    }
    
    /**
     * Check if booking is already delivered
     * @param idBooking
     * @return 
     */
    public boolean canServeBooking(long idBooking) {
        
        boolean flag = false;
        
        Booking b = PersistenceContext.repositories().booking().findOne(idBooking).get();
        
        if(b.getBookingState().actualState().compareTo(BookingState.BookingStates.NOT_SERVED) == 0) flag = true;
        
        return flag;
    }
    
    /**
     * References the correct session to proceed
     * @param cashier 
     */
    private void useSession(){
        this.session = PersistenceContext.repositories().deliveryMealRepository().findYourSession(this.cashier).get();
    }
    
    public SystemUser user(){
        return this.cashier;
    }
}
