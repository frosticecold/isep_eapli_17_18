/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author André Oliveira 1040862
 */
public class ClosePOSController implements Controller {

    private final ListAvailableMealsService availableMealsService = new ListAvailableMealsService();

    public void closeSession() {
        AuthorizationService.clearSession();
    }

    public Map<DishType, Long> listDeliveredMeals(Calendar cal, MealType mt) {
        Map<DishType, Long> calcDeliveredStatistics = availableMealsService.calcDeliveredStatistics(cal, mt);
        return calcDeliveredStatistics;
    }
}
