package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    private final ListAvailableMealsService list; //Listing Services
    private final DeliveryMealSession session;
    
    public RegisterMealDeliveryController(DeliveryMealSession session) {
        
        this.list = new ListAvailableMealsService();
        this.session = session;
    }
    
    /**
     * Method that will focus on recording a new meal delivery on persistence
     * saving the booking which will be delivered, the client who made the booking and the POS where it was delivered
     * @param idClient
     * @param idBooking Booking which will be delivered
     */    
    public void registerNewMealDelivery(String idClient, long idBooking) {
        
        //obtain the booking
        Booking booking = PersistenceContext.repositories().booking().findOne(idBooking).get();
        
        //transform string into Username
        
        Username username = new Username(idClient);

        //obtain the client
        
        SystemUser client = PersistenceContext.repositories().users().findOne(username).get();
        
        //add new record of the delivery just made on DeliveryRegistry
        
        DeliveryRegistry registry = new DeliveryRegistry(session, client, booking);
        
        //persist this Registry
        
        //PersistenceContext.repositories().deliveryRegistryRepository().save(registry);
        
        //change state of the booking just recorded - to served
        
        PersistenceContext.repositories().booking().findOne(idBooking).get().getBookingState().changeToServed();
        
        //get id the meal associated  to the booking
        Meal meal = PersistenceContext.repositories().booking().findOne(idBooking).get().getMeal();
        
        //update dish quantity of execution
        
        PersistenceContext.repositories().executions().findExecutionByMeal(meal);
    }
    
    /**
     * Returns all AvaliableMealStatics of this certain session to list on UI
     * @param session
     * @return 
     */
    public AvailableMealsStatistics showCurrentStatics() {
        
        MealType sessionType;
        
        if(this.session.isLunch()) sessionType = MealType.LUNCH;
        else sessionType = MealType.DINNER;
        
        Calendar ca = this.session.date();

        return new AvailableMealsStatistics(ca, sessionType);
    }
    
}
