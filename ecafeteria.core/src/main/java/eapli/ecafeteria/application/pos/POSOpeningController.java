package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Class 2DC 
 */
public class POSOpeningController {
    
    private final DeliveryMealSessionRepository jpaDMS = PersistenceContext.repositories().deliveryMealRepository();
    private final POSRepository jpaPOS = PersistenceContext.repositories().posRepository();
    private POS pointofsale;
    private final SystemUser user;
    
    public POSOpeningController(){
            user = AuthorizationService.session().authenticatedUser();
            pointofsale = new POS(user);
    }
    
    public boolean checkPoSState(){
        return pointofsale.isClosed();
    }
    public boolean createDeliveryMealSession() {
        
        try {
            POS jpa = jpaPOS.save(pointofsale);
            DeliveryMealSession dms = new DeliveryMealSession(jpa);    
            jpaDMS.save(dms);
            System.out.println("Delivery meal session created successfully.");
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Error inserting into database.: " + ex.getMessage());
        }
        return false;
    }
    
 
}

