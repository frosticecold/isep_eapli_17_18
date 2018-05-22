package eapli.ecafeteria.application.KitchenAlert;

import eapli.ecafeteria.domain.KitchenAlert.WatchDog;
import eapli.framework.application.Controller;

public class KitchenAlertController implements Controller{

    private WatchDog myWatchDog;

    public KitchenAlertController() {
        myWatchDog = WatchDog.getInstance();
    }
    
  


            
    
}
