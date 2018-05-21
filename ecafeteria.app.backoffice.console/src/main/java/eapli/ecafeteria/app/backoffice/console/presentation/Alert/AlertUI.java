package eapli.ecafeteria.app.backoffice.console.presentation.Alert;

import eapli.ecafeteria.application.KitchenAlert.KitchenAlertController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.KitchenAlert.KitchenAlertImp;
import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;


public abstract class AlertUI extends AbstractUI implements Observer{
    
    private KitchenAlertList CURRENT_ALERT = new KitchenAlertList();
    private WatchDog myDog = WatchDog.getInstance();
 //   private KitchenAlertController controller = new KitchenAlertController();

    @Override
    public String headline() {
        
        myDog.addObserver(this);
        
        return  "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() 
                + "]   \n" + CURRENT_ALERT;
    }
    
    @Override
    public void update(Observable o, Object o1) {
        
        if( o instanceof WatchDog){
            WatchDog currentWatchDog = (WatchDog) o;
            
            
            if(o1 instanceof KitchenAlertImp){
                CURRENT_ALERT.add(  (KitchenAlertImp) o1);
                
                System.out.println("\n\nWARNING: NOVO ALERTA\n\n");
            }else{
                
                CURRENT_ALERT = null;
            }
                
                
            
        }
    }
    
    
    
    
    
    
}
