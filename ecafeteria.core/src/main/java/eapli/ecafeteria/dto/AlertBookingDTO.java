package eapli.ecafeteria.dto;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.dto.DTO;

/**
 *
 * @author DAVID
 */
public class AlertBookingDTO implements DTO{

    public Meal meal;
    public int  nBookings;
    public int  nPlanned;

    public AlertBookingDTO(Meal meal, int nBookings, int nPlanned) {
        
        this.meal = meal;
        this.nBookings = nBookings;
        this.nPlanned = nPlanned;
    }

    @Override
    public String toString() {
        return "ALERT DATA:\n" + "meal=" + meal + "\nBookings=" + nBookings + "\nnPlanned=" + nPlanned + '}';
    }
    
    
    
    
    
}
