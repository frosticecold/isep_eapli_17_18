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
import java.util.Optional;

/**
 *
 * @author Class 2DC
 */
public class POSOpeningController {

    private final DeliveryMealSessionRepository jpaDMS = PersistenceContext.repositories().deliveryMealRepository();
    private final POSRepository jpaPOS = PersistenceContext.repositories().posRepository();
    private POS pointofsale;
    private final SystemUser user;

    public POSOpeningController() {
        user = AuthorizationService.session().authenticatedUser();
    }

    public boolean checkPoSState() {
        return pointofsale.isClosed();
    }

    public boolean createDeliveryMealSession() throws DataConcurrencyException, DataIntegrityViolationException {
        Optional<POS> POSinDatabase = jpaPOS.findOne(Long.valueOf("1"));
        if (!POSinDatabase.isPresent()) {
            pointofsale = new POS(user);
        } else {
            pointofsale = POSinDatabase.get();
            if(!checkPoSState()) return false;
        }
        
        pointofsale.changeState();
        POS jpa = jpaPOS.save(pointofsale);
        DeliveryMealSession dms = new DeliveryMealSession(jpa);
        jpaDMS.save(dms);
        return true;
    }
}
