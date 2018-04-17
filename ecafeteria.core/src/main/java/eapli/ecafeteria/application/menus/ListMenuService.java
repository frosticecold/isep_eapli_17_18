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
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> findMenuWithinPeriod(Calendar startDate, Calendar endDate) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return this.menuRepository.findAll();
    }

    public Iterable<Meal> getMealsPublishedByDay(Calendar date, MealType mealType) {

       return menuRepository.listMealsPublishedMenu(date, mealType);
      
    }

}
