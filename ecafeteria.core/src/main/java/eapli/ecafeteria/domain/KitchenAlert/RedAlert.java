package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;

/**
 *
 * @author DAVID
 */
public class RedAlert extends KitchenAlertImp{

    public RedAlert(Meal meal, float ratio) {
        super(meal, ratio);
    }

    @Override
    public String alertType() {
        return String.format("RED ALERT: %s", super.toString());
    }
    
}
