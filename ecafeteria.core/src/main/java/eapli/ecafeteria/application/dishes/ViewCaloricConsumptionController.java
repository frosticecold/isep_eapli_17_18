/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewCaloricConsumptionController implements Controller {

    private final DishRepository dishRepo = PersistenceContext.repositories().dishes();

    public List<Dish> findServedDishBetween(Calendar initialDate, Calendar finalDate) {
        List<Dish> dishes = new ArrayList<>();
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        dishRepo.findServedDishesBetween(initialDate, finalDate);
        return dishes;
    }

    public double getCaloricConsuption(Calendar initialDate, Calendar finalDate) {
        double calories = 0;
        for(Dish dish : findServedDishBetween(initialDate,finalDate)){
            calories = dish.nutricionalInfo().calories();
        }
        return calories;
    }

}
