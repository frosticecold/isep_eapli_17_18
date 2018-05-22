package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByTypeController implements Controller {

    private static final int LUNCH = 1;
    private static final int DINNER = 2;

    public DeliveredMealsByTypeController() {

    }

    /**
     * Returns a list to be printed of all delivered meals by type
     *
     * @param type
     * @return
     */
    public String prepareListDeliveredMealByType() {

        MealType lunch = this.createMealType(LUNCH);

        MealType dinner = this.createMealType(DINNER);

        String msg = "=========================================================== DELIVERED MEALS BY MEAL TYPE ========================================";

        msg += "\nLUNCH : \n\n";

        List<DeliveryRegistry> list = PrevisionsService.deliveredMealsByType(lunch);

        if (list.isEmpty()) {

            msg += "No delivered meals for lunch\n";
        } else {
            for (int l = 0; l < list.size(); l++) {

                msg += list.get(l).booking().getMeal().toString() + "\n" + list.get(l).toString() + "\n";

                msg += "LISTED " + list.size() + " OF DELIVERED MEALS FOR LUNCH \n";
            }
        }

        msg += "DINNER : \n\n";

        List<DeliveryRegistry> list2 = PrevisionsService.deliveredMealsByType(dinner);

        if (list2.isEmpty()) {
            msg += "No delivered meals for dinner\n";
        } else {

            for (int d = 0; d < list2.size(); d++) {

                msg += list2.get(d).booking().getMeal().toString() + "\n" + list2.get(d).toString() + "\n";

                msg += "LISTED " + list2.size() + " OF DELIVERED MEALS FOR DINNER \n";
            }
        }
        msg += "=====================================================================================================================\n";

        return msg;
    }

    /**
     * Creates a specific meal type
     *
     * @param type
     * @return
     */
    private MealType createMealType(int type) {

        MealType mT = null;

        switch (type) {
            case 1:
                mT = MealType.LUNCH;
                break;

            case 2:
                mT = MealType.DINNER;
                break;
        }

        return mT;
    }
}
