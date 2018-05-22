package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByDateController implements Controller {

    private PrevisionsService service;

    public DeliveredMealsByDateController() {

        this.service.getInstance();
    }

    /**
     * Prepares string to UserInterface
     *
     * @param date
     * @return
     */
    public String prepareListOfDeliveredMealsByDate(Calendar date) {

        String dateCorrect = date.get(Calendar.DAY_OF_MONTH) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.YEAR);

        String msg = "================================================ DELIVERED MEALS OF DATE : " + dateCorrect + "=============================\n";

        List<DeliveryRegistry> list = this.service.deliveredMealsByDate(date);

        if (list.isEmpty()) {

            msg += "No delivered meals for this day";
        } else {
            for (int i = 0; i < list.size(); i++) {

                msg += "DELIVERY : \n" + list.get(i).toString() + "\n";

                Meal m = list.get(i).booking().getMeal();

                msg += m.toString() + "\n";
            }

            msg += "\n\n LISTED " + list.size() + " DELIVERYS OF DATE " + dateCorrect + "\n";

        }

        return msg;
    }

}
