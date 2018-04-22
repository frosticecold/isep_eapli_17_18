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
import eapli.ecafeteria.persistence.MealReportRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Optional;

public class MenuService {

    private static final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private static final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public MenuService() {
    }

    public static Optional<Menu> findMenuWithinPeriod(Calendar startDate, Calendar endDate) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return menuRepository.findMenuWithinPeriod(startDate, endDate);
    }

    public static Iterable<Meal> getMealsPublishedByDay(Calendar date, MealType mealType) {
        
        return mealRepository.listOfMealsByDateAndMealType(date, mealType);
    }

    public static Iterable<Calendar> getWorkingDaysOfMenu(Menu m) {

        return m.getWorkWeekDaysIterable();
    }

    public static Iterable<Meal> getMealsFromMenuByGivenDay(final Menu menu, final Calendar cal) {
        return mealRepository.listMealsFromMenuByGivenDay(menu, cal);
    }
    
     public static Optional<Menu> findLatestMenu(){
        return menuRepository.findLatestMenu();
    }

}
