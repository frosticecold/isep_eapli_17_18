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
public class KitchenAlertService extends Observable {

    private AlertRepositoryBookings myBookings;
    private AlertRepositoryLimits myLimits;

    public KitchenAlertService(AlertRepositoryLimits limits, AlertRepositoryBookings bookings) {
        myBookings = bookings;
        myLimits = limits;
    }

    List<KitchenAlertImp> calculateQuotient() {

        float[] limits = {0.7f, 0.9f}; // não estava implementado as 23:50 do dia 23/05
        //List<AlertBookingDTO> dtoList =  new ArrayList<>();

        //        dtoList.add(new AlertBookingDTO(new Meal(new Dish(new DishType("Ola", "nice")
//                                                , null, null)
//                                        , MealType.LUNCH, Calendar.getInstance(), null), 10, 10));

        List<AlertBookingDTO> dtoList = myBookings.getNOBookings();

        List<KitchenAlertImp> alerts = new ArrayList<>();

        for (AlertBookingDTO alert : dtoList) {
            if (alert != null) {
                Float nBook = (float) alert.nBookings;
                Float nPlan = (float) alert.nPlanned;
                alerts.add(AlertFactory.buildAlert((nBook/nPlan), limits[0], limits[1], alert.meal));
            }
        }

        return alerts;
    }

}
