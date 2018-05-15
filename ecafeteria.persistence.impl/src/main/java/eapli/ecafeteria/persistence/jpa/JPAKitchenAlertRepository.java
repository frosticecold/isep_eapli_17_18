package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.persistence.KictchenAlertRepository;
import java.util.HashMap;
import java.util.Map;


public class JPAKitchenAlertRepository implements KictchenAlertRepository{

    @Override
    public Map<Integer, Double> checkBookings() {
        
        /*
        QUERY: \_(^_^)_/
        
        SELECT m.id as MEAL_ID , COUNT(*) as N_OF_BOOKINGS , mpi.QUANTITY  FROM BOOKING b,  MENUPLANITEM mpi, MEAL m
        WHERE b.meal_id = mpi.currentmeal_id
        AND b.bookingstate = 'NOT_SERVED'
        AND b.meal_id = m.id
        GROUP BY m.id
        
        */
        
        
        
        
        return new HashMap<>();
    }
    

    
}
