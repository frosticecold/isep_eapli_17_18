package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByDishController implements Controller {

    private PrevisionsService service;

    public DeliveredMealsByDishController() {

       this.service = new PrevisionsService();
    }

    /**
     * Checks if a certain dish exits
     *
     * @param dishName
     * @return
     */
    public boolean checkDish(String dishName) {

        boolean flag = false;

        try {
            Dish d = PersistenceContext.repositories().dishes().findByName(Designation.valueOf(dishName)).get();
            flag = true;
        } catch (Exception e) {

            flag = false;
        }

        return flag;
    }

    /**
     * Returns a certain dish
     *
     * @param dishName
     * @return
     */
    private Dish getDish(String dishName) {

        Dish d = PersistenceContext.repositories().dishes().findByName(Designation.valueOf(dishName)).get();

        return d;
    }

    /**
     * Returns a list of delivered meals by a certain dish
     *
     * @param dishName
     * @return
     */
    public String prepareDeliveredMealsByDish(String dishName) {

        String msg = "======================================================== DELIVERED MEALS BY DISH =================================================\n";

        Dish d = this.getDish(dishName);

        msg += " DISH : " + d.toString() + "\n DELIVERIES : \n";

        List<DeliveryRegistry> list = this.service.deliveredMealsByDish(d);

        if (list.isEmpty()) {

            msg += "Nothing to show\n";
        } else {
            for (int i = 0; i < list.size(); i++) {

                msg += list.get(i).toString() + "\n";
            }

            msg += "LISTED " + list.size() + " DELIVERED MEALS\n";

        }

        msg += "=========================================================================================================================\n";

        return msg;
    }

}
