package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;

public abstract class KitchenAlertImp implements KitchenAlert{
    
    Meal meal;
    float ratio;

    public KitchenAlertImp(Meal meal, float ratio) {
        this.ratio = ratio;
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "KitchenAlertImp{" + "meal=" + meal + ", ratio=" + ratio + '}';
    }
    
    


    
    
    
    
    
    
    
    
}
