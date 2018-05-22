package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class MealsByMenuPlanController implements Controller {



    public MealsByMenuPlanController() {


    }

    /**
     * returns a list of meals planned for a certain menu
     *
     * @return
     */
    public String getMealsByMenuPlan() {

        String msg = "======================== Meals Planned for Active Menus ===================\n";

        List<Meal> mealList = PrevisionsService.prepareMealsByMenuPlanList();

        for (int i = 0; i < mealList.size(); i++) {

            msg += mealList.get(i).toString() + "\n";
        }

        msg += "Quantity of meals listed : " + mealList.size() + "\n";

        msg += "\n=======================================================================================\n";

        return msg;
    }

}
