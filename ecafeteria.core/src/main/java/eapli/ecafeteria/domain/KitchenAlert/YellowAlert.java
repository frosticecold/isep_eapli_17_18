package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;


public class YellowAlert extends KitchenAlertImp{

    YellowAlert(Meal m, float percentage) {
        super(m,percentage);
    }

    @Override
    public String alertType() {
        return String.format("YELLOW ALERT: %s", super.toString());
    }

  
    
}
