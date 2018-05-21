
package eapli.ecafeteria.app.backoffice.console.presentation.Alert;

import eapli.ecafeteria.domain.KitchenAlert.KitchenAlert;
import eapli.ecafeteria.domain.KitchenAlert.RedAlert;
import eapli.ecafeteria.domain.KitchenAlert.YellowAlert;
import java.util.ArrayList;
import java.util.List;


public class KitchenAlertList {
    
    private List<KitchenAlert> myList;

    public KitchenAlertList(List<KitchenAlert> myList) {
        this.myList = myList;
    }

    public KitchenAlertList() {
        myList = new ArrayList<>();
    }
    
    public void add(KitchenAlert ka){
        
        if( ka != null){
            
          myList.add(ka);
        }
    }
    
    public void addAll(List<KitchenAlert> newAlerts){
        
        for( KitchenAlert alert : newAlerts){
            if( alert != null){
                add(alert);
            }
        }
    }
    
    public void addAll(KitchenAlertList newList){
        
        if( newList != null && !newList.myList.isEmpty()){
            
            this.addAll(newList.myList);    
        }
    }
    
    

    @Override
    public String toString() {
        String output="";
       
        if( myList.isEmpty()){
            
            output = "Não existem alertas";
        }
        
        for( KitchenAlert k : myList){
            
            if( k instanceof YellowAlert){ /* é obrigatoriamente deste tipo */
                
                YellowAlert yellow = (YellowAlert) k;
                
                output += "\n" + yellow.alertType();
                
            }else{
                
                RedAlert red = (RedAlert) k;
                
                  output += "\n" + red.alertType();
                
            }
            
            
        }
        
        return output;
    }
    
    
    
}
