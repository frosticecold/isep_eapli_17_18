/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class CopyMenuController implements Controller {

    private final MenuRepository menurepo = PersistenceContext.repositories().menus();
    private final MealRepository mealrepo = PersistenceContext.repositories().meals();
    private final DishRepository dishrepo = PersistenceContext.repositories().dishes();
    private final DishTypeRepository dishtyperepo = PersistenceContext.repositories().dishTypes();
    private final TransactionalContext tx = PersistenceContext.repositories().buildTransactionalContext();
    private final MealRepository mealTx = PersistenceContext.repositories().mealRepositoryAutoTx(tx);
    private Menu m_menu;
    private Calendar selectedDay;
    private Iterable<DishType> listOfDishTypes;

    public Iterable<Menu> getAvailableMenus() {
        return menurepo.findAll();
    }

    public Iterable<Meal> findAllMeals(Menu menu) {
        return mealrepo.findMealsByMenu(menu);
    }

    public Iterable<Meal> changeMealsToNewMenu(Iterable<Meal> list, Menu newMenu, Menu oldMenu) {
        Iterable<Calendar> oldMenuDays = oldMenu.getWorkWeekDaysIterable();
        Map<Integer, List<Meal>> mapOfMeals = new LinkedHashMap<>();

        for (Calendar c : oldMenuDays) {
            mapOfMeals.put(c.get(Calendar.DAY_OF_YEAR), new ArrayList<>());
        }
        for (Meal m : list) {
            Calendar mealDate = m.getMealDate();
            int dayOfMeal = mealDate.get(Calendar.DAY_OF_YEAR);
            mapOfMeals.get(dayOfMeal).add(m);
        }

        Map<Calendar, Integer> mapDaysAssociated = new LinkedHashMap<>();
        Iterable<Calendar> workWeekDaysIterable = newMenu.getWorkWeekDaysIterable();
        Iterator<Calendar> iterator = workWeekDaysIterable.iterator();
        for (Integer i : mapOfMeals.keySet()) {
            mapDaysAssociated.put(iterator.next(), i);
        }

        List<Meal> newMealsList = new ArrayList<>();
        for (Calendar c : workWeekDaysIterable) {
            Integer Day = mapDaysAssociated.get(c);
            List<Meal> listOfMeals = mapOfMeals.get(Day);
            for (Meal m : listOfMeals) {
                Meal newMeal = new Meal(m, c, newMenu);
                newMealsList.add(newMeal);
            }
        }
        return newMealsList;
    }

    public Menu createOrFindMenu(final Calendar initialDate, final Calendar finalDate) throws DataIntegrityViolationException, DataConcurrencyException {
        if (initialDate == null || finalDate == null) {
            throw new IllegalArgumentException("Dates must not be null.");
        }
        Menu m = findMenu(initialDate, finalDate);
        if (m == null) {
            try {
                m_menu = new Menu(initialDate, finalDate);
                m_menu = saveMenu(m_menu);

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ElaborateOrEditMenuController.class
                        .getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            m_menu = m;
        }
        return m_menu;
    }

    private Menu findMenu(final Calendar initialDate, final Calendar finalDate) {
        Optional<Menu> opt = menurepo.findMenuWithinPeriod(initialDate, finalDate);
        try {
            Menu menu = opt.get();
            return menu;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public Menu saveMenu(Menu menu) throws DataIntegrityViolationException, DataConcurrencyException {
        if (menu == null) {
            throw new IllegalArgumentException("Menu must not be null.");
        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        Menu savedMenu = menurepo.save(menu);
        return savedMenu;
    }

    public Calendar selectDay(Calendar cal) {
        if (cal == null) {
            return null;
        }
        selectedDay = cal;
        return selectedDay;
    }

    public boolean addMealOnMenu(Menu menu, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        if (menu == null || meal == null) {
            return false;
        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (menu.isPublished()) {
            throw new IllegalStateException("Cannot save published menu.");
        }
        return mealrepo.save(meal) != null;
    }

    public boolean removeMealFromMenu(final Menu menu, final Meal meal) throws DataIntegrityViolationException {
        if (menu == null || meal == null) {
            return false;

        }
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (menu.isPublished()) {
            throw new IllegalStateException("Cannot save published menu.");
        }
        mealrepo.delete(meal);
        return true;
    }

    public boolean saveAllMeals(Iterable<Meal> list) throws DataConcurrencyException, DataIntegrityViolationException {
        tx.beginTransaction();
        for (Meal m : list) {
            mealTx.save(m);
        }
        tx.commit();
        return true;
    }

    public Iterable<Dish> getDishesByDishType(DishType dishtype) {
        if (dishtype == null) {
            return null;
        }
        return dishrepo.findByDishType(dishtype);
    }

    public Iterable<DishType> getDishTypes() {
        if (listOfDishTypes == null) {
            listOfDishTypes = dishtyperepo.activeDishTypes();
        }
        return listOfDishTypes;
    }
}
