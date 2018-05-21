package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observer;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchDog extends Observable implements Observer{
    
    private static WatchDog myWatchDog;
    private static final float TIME_IN_SECONDS = 10; // 
    private KitchenAlertService kAlert;
    
    private final AlertRepositoryBookings alertRepositoryBookings = PersistenceContext.repositories().alertRepositoryBookings() ;
    private final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;

    public WatchDog() {
        
       
        
        kAlert = new KitchenAlertService(alertRepositoryLimits, alertRepositoryBookings);
        kAlert.addObserver(this); 
        //verificar isto pq continuo a achar que nao se pode fazer no construtor
        
        
        new Thread(){
            @Override
            public void run() {
                while(true){
                    
                    try {
                        testLimits();
                        Thread.sleep((long) TIME_IN_SECONDS);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WatchDog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                    
            
        };
    }

    void testLimits(){
        
        kAlert.calculateX();
        
    }

    public static WatchDog getInstance(){
        
        if( myWatchDog == null){
            
            myWatchDog = new WatchDog();
        }     
            return myWatchDog;
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    


    
}
