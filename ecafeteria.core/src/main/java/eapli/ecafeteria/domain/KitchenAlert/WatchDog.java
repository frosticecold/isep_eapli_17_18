package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
//import eapli.framework.actions.singleton.SingletonStrategy;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


/*
DÚVIDA: Porquê que não se pode usar interface para metodos static?
*/
public class WatchDog extends Observable implements Runnable{
    
    private static WatchDog myWatchDog;
    private static KitchenAlertService kAlert;
    
    private static final float TIME_IN_MINUTES = 1; 
    

    
    public static WatchDog getInstance() {/* IS A SINGLETON */
        
        if( myWatchDog == null){
            
            myWatchDog = new WatchDog();
        }
        
        
        
        return myWatchDog;
    }
   
    
    private WatchDog() { /* CONSTRUCTOR IS PRIVATE BCS ONLY WATCHDOG CAN CREATE ITSELF */
        
        dormir(); /* quero quando inicio o watchdog domir */
    }
    
    
    
    
    // ^ IS SINGLETON

   
    public void createAlertService(AlertRepositoryBookings alertRepositoryBookings, AlertRepositoryLimits alertRepositoryLimits) {
        
          
        kAlert = new KitchenAlertService(alertRepositoryLimits, alertRepositoryBookings);
    }
  
    @Override
    public void run() {
        
            testLimits();
            
    }
    
    
    private void testLimits(){
        
        List<KitchenAlertImp> alerts = kAlert.calculateQuotient();
      
        
        if( !alerts.isEmpty() ){
            
            setChanged();
            notifyObservers(alerts);
           
        }
        
        
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

    private void dormir() { /* DORME POR 1 SEGUNDO */
        
        long startTime = System.currentTimeMillis();
        long now;
        
        for (int count = 0;; count++) {
            
            now = System.currentTimeMillis();
            if (now - startTime >= 1000) {

                break;
            }
        }

    }


    }




    

