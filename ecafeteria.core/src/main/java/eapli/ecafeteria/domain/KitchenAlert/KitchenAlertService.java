package eapli.ecafeteria.domain.KitchenAlert;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.framework.domain.money.Money;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author DAVID
 */
public class KitchenAlertService extends Observable{
    
    private AlertRepositoryBookings myBookings;
    private AlertRepositoryLimits myLimits;

    public KitchenAlertService(AlertRepositoryLimits limits, AlertRepositoryBookings bookings) {
        myBookings = bookings;
        myLimits = limits;
    }
    
    KitchenAlert calculateX(){
        
        float[] limits =  {0.7f , 0.9f};                     //myLimits.getLimits();
        List<AlertBookingDTO> dtoList =  new ArrayList<>();//myBookings.getNOBookings();
        
        dtoList.add(new AlertBookingDTO(new Meal(new Dish(new DishType("Ola", "nice")
                                                , null, null)
                                        , MealType.LUNCH, Calendar.getInstance(), null), 10, 10));
        
        System.out.println("VAI MANEL\n");
        System.out.println(dtoList);
        
        return AlertFactory.buildAlert(1, limits[0], limits[1], dtoList.get(0).meal);
    }


    
    
}
