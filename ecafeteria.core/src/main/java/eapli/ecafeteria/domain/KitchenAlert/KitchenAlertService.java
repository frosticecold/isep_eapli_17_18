package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;

/**
 *
 * @author DAVID
 */
public class KitchenAlertService {
    
    private AlertRepositoryBookings myBookings;
    private AlertRepositoryLimits myLimits;

    public KitchenAlertService(AlertRepositoryLimits limits, AlertRepositoryBookings bookings) {
        myBookings = bookings;
        myLimits = limits;
    }
    
    KitchenAlert calculateX(){
        return AlertFactory.buildAlert(1, 2, 3);
    }
    
    
}
