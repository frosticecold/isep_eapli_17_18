package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WatchDog extends Observable implements Runnable{
    
    private static WatchDog myWatchDog;
    private static final float TIME_IN_MINUTES = 1; 
    private static KitchenAlertService kAlert;
    private static final AlertRepositoryBookings alertRepositoryBookings = PersistenceContext.repositories().alertRepositoryBookings() ;
    private static final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;
    
    public static WatchDog getInstance(){ /* IS A SINGLETON */
        
        if( myWatchDog == null){
            
            myWatchDog = new WatchDog();
        }     
            return myWatchDog;
    }
    
    private WatchDog() { /* CONSTRUCTOR IS PRIVATE BCS ONLY WATCHDOG CAN CREATE ITSELF */
        
       createAlertService(alertRepositoryBookings, alertRepositoryLimits);
      
        
        runThread(); /* runs thread every TIME_IN_MILISECONDS */
      //  dormir(); /* quero quando inicio o watchdog domir */
        
    }


    void testLimits(){
        
        List<KitchenAlertImp> alerts = kAlert.calculateX();
      
        
        if( !alerts.isEmpty() ){
            
            setChanged();
            notifyObservers(alerts);
           
        }
        
        
    }

 
    
    

    private void createAlertService(AlertRepositoryBookings alertRepositoryBookings, AlertRepositoryLimits alertRepositoryLimits) {
        
          
        kAlert = new KitchenAlertService(alertRepositoryLimits, alertRepositoryBookings);
    }

   
    @Override
    public void run() {
        
            testLimits();
            
    }


    private void runThread() {
        
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                WatchDog.getInstance().run();
                
               
            }
        }, 0, (long) (1000 * 60 * TIME_IN_MINUTES));
        
        
        
    }

    
    


    
}
