package eapli.ecafeteria.domain.KitchenAlert;

/**
 *
 * @author DAVID
 */
public class AlertFactory {
    
    public static KitchenAlert buildAlert(float percentage, float l1, float l2){
        
        if( Float.compare(percentage, l1) < 0){
            
            return null;
        }else if( Float.compare(percentage, l2) >= 0){
            
            return new RedAlert();
                
        }else{
            
            return new YellowAlert();
        }
        
        }
   
    
}
