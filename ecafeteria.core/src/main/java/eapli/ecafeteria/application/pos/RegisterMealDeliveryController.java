package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController extends ViewAvailableMealsController implements Controller {
        
    /** Constructor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    private DeliveryMealSession session;
    private SystemUser cashier;
//    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
//    private final DeliveryRegistryRepository deliveryRegistryRepo = PersistenceContext.repositories().deliveryRegistryRepository();
//    //private final BookingRepository bookingsRepo = PersistenceContext.repositories().booking(txCtx);
    private final CafeteriaUserRepository cafeteriaUsersRepo = PersistenceContext.repositories().cafeteriaUsers(); //doesnt user transaction context because it wont change or persist any changes on database
    
    public RegisterMealDeliveryController() {
        this.cashier = AuthorizationService.session().authenticatedUser();
        session = null;
    }
    
    /**
     * Method that will focus on recording a new meal delivery on persistence
     * saving the booking which will be delivered, the client who made the booking and the POS where it was delivered
     * @param idClient
     * @param idBooking Booking which will be delivered
     */    
    public void registerNewMealDelivery(String number, long idBooking)  {
        
        this.useSession();
        
        //transform string into MecanographicNumber
            
        MecanographicNumber mNumber = new MecanographicNumber(number);

        //obtain the client
        
        CafeteriaUser client = this.cafeteriaUsersRepo.findByMecanographicNumber(mNumber).get();
        
        //get booking by the number of the user
        

        
        
        //add new record of the delivery just made on DeliveryRegistry
        
        //DeliveryRegistry registry = new DeliveryRegistry(this.session, client, booking);
        

        
        //change state of the booking just recorded - to served
        
        

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
     * Returns booking by finding the user who payed for it
     */
    public boolean getBooking(long idBooking) {
        
        Booking b = PersistenceContext.repositories().booking().findOne(idBooking).get(); 
        
        if(b == null) return false;
        else return true;
    }
    
    /**
     * Check if booking is already delivered
     * @param idBooking
     * @return 
     */
    public boolean canServeBooking(long booking) {
        
        boolean flag = false;
        
        if(PersistenceContext.repositories().booking().findOne(booking).get().getBookingState().actualState().compareTo(BookingState.BookingStates.BOOKED) == 0) flag = true;
        
        return flag;
    }
    
    /**
     * References the correct session to proceed
     * @param cashier 
     */
    private void useSession(){
        
        DeliveryMealSession s = PersistenceContext.repositories().deliveryMealRepository().findYourSession(this.cashier).get();

        if(s!= null) this.session = s;
    }
}
