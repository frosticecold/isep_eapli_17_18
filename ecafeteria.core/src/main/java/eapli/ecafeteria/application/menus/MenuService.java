/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuService {

    private static final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private static final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private static final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    public MenuService() {
    }

    public static List<Meal> findMenuWithinPeriod() {
        Calendar cal = Calendar.getInstance();
        Menu menu = menuRepository.findMenuOnDate(cal).get();
        return mealRepository.findMealsByMenu(menu);
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

    public static List<Menu> findLatestMenus() {
        return menuRepository.findLatestMenus();
    }
    
    /**
     * List valid menus based on the mealTypes and dishTypes per day
     * @return a list of valid menus
     */
    public static Iterable<Menu> findValidMenus() {
        List<Menu> list = new ArrayList<>();

        final int mealTypes = MealType.values().length;
        Iterable<DishType> dishTypes = dishTypeRepository.activeDishTypes();

        for (Menu menu : menuRepository.listUnpublishedMenus()) {
            boolean validDay = true;
            for (Calendar calendar : getWorkingDaysOfMenu(menu)) {
                Map<MealType, Set<DishType>> map = new HashMap<>();
                for (MealType value : MealType.values()) {
                    map.put(value, new HashSet<>());
                }
                for (Meal meal : getMealsFromMenuByGivenDay(menu, calendar)) {
                    map.get(meal.mealtype()).add(meal.dish().dishType());
                }

                boolean valid = true;
                for (Map.Entry<MealType, Set<DishType>> entry : map.entrySet()) {
                    if (!entry.getValue().containsAll((Collection<?>) dishTypes)) {
                        valid = false;
                    }
                }
                if (map.keySet().size() != mealTypes || !valid) {
                    validDay = false;
                }
            }
            if (validDay) {
                list.add(menu);
            }
        }
        return list;
    }
}
