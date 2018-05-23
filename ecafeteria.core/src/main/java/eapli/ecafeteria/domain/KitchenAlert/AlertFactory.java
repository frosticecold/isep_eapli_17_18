package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;


public class AlertFactory {

    public static KitchenAlertImp buildAlert(float percentage, float amarelo, float vermelho, Meal m) {

        if (Float.compare(percentage, amarelo) < 0) {

            return null;
            
        } else if (Float.compare(percentage, vermelho) >= 0) {

            return new RedAlert(m, percentage);

        } else {

            return new YellowAlert(m, percentage);
        }

    }

}
