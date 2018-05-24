package eapli.ecafeteria.application.KitchenAlert;

import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

public class KitchenAlertController implements Controller{

    private WatchDog myWatchDog;
    private final AlertRepositoryBookings alertRepositoryBookings = PersistenceContext.repositories().alertRepositoryBookings() ;
    private final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;

    public KitchenAlertController(WatchDog myWatchDog) {
        this.myWatchDog = myWatchDog;
    }
    
    public void createAlertService() {
        
       myWatchDog.createAlertService(alertRepositoryBookings, alertRepositoryLimits);
    }
    
    public void startThread(){
        
        myWatchDog.run();
    }

   

  


            
    
}
