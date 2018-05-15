package eapli.ecafeteria.application.kitchen;

/**
* SIGLETONé
*/
public class KitchenWatchDogo {
    
    public KitchenWatchDogo() {
        
        while(true){
            
            
        }
    
    }
    
    public static KitchenWatchDogo getInstance() {
        return KitchenWatchDogoHolder.INSTANCE;
    }
    
    private static class KitchenWatchDogoHolder {

        private static final KitchenWatchDogo INSTANCE = new KitchenWatchDogo();
        
                
    
    }
    }

