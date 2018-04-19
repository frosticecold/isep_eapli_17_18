/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Optional;

public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Optional<Menu> findMenuWithinPeriod(Calendar startDate, Calendar endDate) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return this.menuRepository.findMenuWithinPeriod(startDate, endDate);
    }

    public Iterable<Meal> getMealsPublishedByDay(Calendar date, MealType mealType) {

       return menuRepository.listMealsPublishedMenu(date, mealType);
      
    }

}
