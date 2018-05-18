package eapli.ecafeteria.application.KitchenAlert;

import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;


public class KitchenAlertController {
 
   

    private WatchDog myWatchDog;
    
    
    public KitchenAlertController(WatchDog wd) {
        myWatchDog = wd;
       
        
    }
    
    
            
    
}
