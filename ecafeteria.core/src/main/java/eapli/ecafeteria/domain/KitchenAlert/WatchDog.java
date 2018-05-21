package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observer;
import java.util.Observable;


public class WatchDog extends Observable implements Observer, Runnable{
    
    private static WatchDog myWatchDog;
    private static final float TIME_IN_MILISECONDS = 10; // 
    private static int status;
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
        System.out.println("ANTES DO RUN");
      // run(); /* runs thread every TIME_IN_MILISECONDS */
    }


    void testLimits(){
        
        kAlert.calculateX();
        
    }

    private void createAlertService(AlertRepositoryBookings alertRepositoryBookings, AlertRepositoryLimits alertRepositoryLimits) {
        
          
        kAlert = new KitchenAlertService(alertRepositoryLimits, alertRepositoryBookings);
        kAlert.addObserver(this); 
    }

   
    @Override
    public void run() {
        while (true) {
            testLimits();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                status =-1;
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    


    
}
