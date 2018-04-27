package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.Optional;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Class 2DC 
 */
public class POSOpeningController {
    
    private final DeliveryMealSessionRepository jpaDMS = PersistenceContext.repositories().deliveryMealRepository();
    private final POSRepository jpaPOS = PersistenceContext.repositories().posRepository();
    private POS pointofsale;
    private final SystemUser user;
    
    public POSOpeningController() throws DataConcurrencyException, DataIntegrityViolationException{
            user = AuthorizationService.session().authenticatedUser();
            pointofsale = createPOS();
    }
    
    public boolean checkPoSState(){
        return pointofsale.isClosed();
    }
    
    private POS createPOS() throws DataConcurrencyException, DataIntegrityViolationException{
        POS temp = new POS(user); 
        jpaPOS.save(temp);
        return temp;
    }

    public void createDeliveryMealSession() {
        try {
            DeliveryMealSession dms = new DeliveryMealSession(pointofsale);
            if(!user.isActive()) return;    
            jpaDMS.save(dms);
            
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(POSOpeningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
}
