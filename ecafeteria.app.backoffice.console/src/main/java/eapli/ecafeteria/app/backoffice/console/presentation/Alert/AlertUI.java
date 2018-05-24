package eapli.ecafeteria.app.backoffice.console.presentation.Alert;

import eapli.ecafeteria.application.KitchenAlert.KitchenAlertController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.KitchenAlert.KitchenAlert;
import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public abstract class AlertUI extends AbstractUI implements Observer{
    
    private KitchenAlertList CURRENT_ALERT = new KitchenAlertList();
    private WatchDog myDog = WatchDog.getInstance();
    private KitchenAlertController controller = new KitchenAlertController(myDog);

    @Override
    public String headline() {
        
        addObserver();
        executeControl();
        
        return  "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() 
                + "] " + CURRENT_ALERT.toString() ;
    }
    
    @Override
    public void update(Observable o, Object o1) {
        
        if( o instanceof WatchDog){
            WatchDog currentWatchDog = (WatchDog) o;
            System.out.println("\n\nWARNING: NOVO ALERTA\n\n");
            
            if(o1 instanceof List){
                
                
                
                CURRENT_ALERT.addAll((List<KitchenAlert>) o1);
                
                
            }else{
                
                CURRENT_ALERT = null;
            }

        }
    }
    
    
    @Override
    protected void drawFormTitle(String title) {
        
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }

    private void addObserver() {
        myDog.addObserver(this);
       
    }

    private void executeControl() {
        
        controller.createAlertService();
        controller.startThread();    
    }
    
    
    
}
