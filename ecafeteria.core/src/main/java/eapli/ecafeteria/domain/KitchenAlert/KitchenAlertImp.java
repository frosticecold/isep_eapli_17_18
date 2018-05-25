package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.util.DateTime;
import java.util.Objects;

public abstract class KitchenAlertImp implements KitchenAlert{
    
    Meal meal;
    float ratio;

    public KitchenAlertImp(Meal meal, float ratio) {
        this.ratio = ratio;
        this.meal = meal;
    }

 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KitchenAlertImp other = (KitchenAlertImp) obj;
        if (!Objects.equals(this.meal.id(), other.meal.id())) {
            return false;
        }
        
        return true;
    }
    
    

    @Override
    public String toString() {
       
        return "\nMEAL" + mealToString(meal) + "\nPERCENTAGEM= " + Math.round(ratio*100) + "%\n";
    }

    private String mealToString(Meal meal) {
        
        String strDate = DateTime.convertCalendarToDayMonthYearAndDayName(meal.getMealDate());
        
        return String.format(" %d\nDish: %s\nMeal Type: %s\nDate:%s", meal.id(), meal.dish().name(), meal.mealtype().toString(), strDate);
    }
    
    


    
    
    
    
    
    
    
    
}
