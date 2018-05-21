package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author DAVID
 */
public class KitchenAlertService extends Observable{
    
    private AlertRepositoryBookings myBookings;
    private AlertRepositoryLimits myLimits;

    public KitchenAlertService(AlertRepositoryLimits limits, AlertRepositoryBookings bookings) {
        myBookings = bookings;
        myLimits = limits;
    }
    
    KitchenAlert calculateX(){
        
        float[] limits = myLimits.getLimits();
        List<AlertBookingDTO> dtoList =  myBookings.getNOBookings();
        
        return AlertFactory.buildAlert(0F, limits[0], limits[1], null);
    }


    
    
}
