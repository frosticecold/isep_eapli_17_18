package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByMealController implements Controller {

    public DeliveredMealsByMealController() {
        
    }

    /**
     * Checks if a certain meal exists by receiving its id
     *
     * @param m
     * @return
     */
    public boolean checkMealExistence(Long idMeal) {

        boolean f = false;
        
        try{
        
            Meal m = PersistenceContext.repositories().meals().findOne(idMeal).get();
            f = true;
        }
        catch(Exception e) {
            
            f = false;
        }

        return f;
    }

    /**
     * Returns a certain meal
     *
     * @param idMeal
     * @return
     */
    private Meal getMeal(Long idMeal) {

        Meal m = PersistenceContext.repositories().meals().findOne(idMeal).get();

        return m;
    }

    public String prepareListDeliveredMealsByMeal(Long idMeal) {
        
        Meal m = this.getMeal(idMeal);

        String msg = "============================= DELIVERED MEALS GROUPED BY MEAL =======================================\n";

        List<DeliveryRegistry> list = PrevisionsService.deliveredMealsByMeal(m);

        if (list.isEmpty()) {

            msg += "Nothing to show\n";
        } else {

            msg += "CURRENT MEAL - " + m.toString() + "\nHas the following deliveries : \n";

            for (int i = 0; i < list.size(); i++) {

                msg += list.get(i).toString() + "\n";
            }

            msg += "TOTAL OF " + list.size() + " DELIVERED MEALS \n";
        }

        msg += "=====================================================================================================================\n";

        return msg;
    }
}
