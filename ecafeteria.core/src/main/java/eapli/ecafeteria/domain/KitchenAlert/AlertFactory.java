package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;

/**
 *
 * @author DAVID
 */
public class AlertFactory {
    
    public static KitchenAlertImp buildAlert(float percentage, float l1, float l2, Meal m){
        
        if( Float.compare(percentage, l1) < 0){
            
            return null;
        }else if( Float.compare(percentage, l2) >= 0){
            
            return new RedAlert(m, percentage);
                
        }else{
            
            return new YellowAlert(m, percentage);
        }
        
        }
   
    
}
