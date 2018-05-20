
package eapli.ecafeteria.app.backoffice.console.presentation.Alert;

import eapli.ecafeteria.domain.KitchenAlert.KitchenAlert;
import java.util.ArrayList;
import java.util.List;


class KitchenAlertList {
    
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
    
    

    @Override
    public String toString() {
        String output="";
       
        if( myList.isEmpty()){
            
            output = "Não existem alertas";
        }
        
        for( KitchenAlert k : myList){
            
            output += "\n" + k.alertType();
            
        }
        
        
        return output;
    }
    
    
    
}
