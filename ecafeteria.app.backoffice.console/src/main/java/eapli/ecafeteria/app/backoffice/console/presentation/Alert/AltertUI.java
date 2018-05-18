package eapli.ecafeteria.app.backoffice.console.presentation.Alert;

import eapli.framework.actions.Action;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;


public abstract class AltertUI extends AbstractUI implements Observer{
    
    public String CURRENT_ALERT = "Não existem alertas."; 

 
    @Override
    public String headline() {
        return CURRENT_ALERT;
    }

    @Override
    public void update(Observable o, Object o1) {
        
        
    }
    
    
    
    
    
    
}
