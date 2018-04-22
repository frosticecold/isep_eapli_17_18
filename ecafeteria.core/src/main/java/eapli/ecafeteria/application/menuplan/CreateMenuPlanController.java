/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.beginningOfWeek;
import static eapli.framework.util.DateTime.currentWeekNumber;
import static eapli.framework.util.DateTime.endOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class CreateMenuPlanController implements Controller {

    private final MenuPlanRepository mpr = PersistenceContext.repositories().menuPlan();

    private Menu m;

    private MenuPlan menuplan;

    private MenuPlanItem mpi;

    private Quantity q;

    public Menu getCurrentMenuWithoutPlan() {

        MenuPlan mp;

        m = MenuService.findLatestMenu().get();
   
       // mp = mpr.getMenuPlanFromMenu(m);

//        if (mp != null) {
//            return null;
//        }

        return m;
    }

    public Iterable<Meal> mealsFromMenuByDay(Calendar bDay, Menu me) {
        return MenuService.getMealsFromMenuByGivenDay(me, bDay);
    }

    public Quantity insertQuantity(int quantity) {
        q = new Quantity(quantity);
        return q;
    }

    public void fillMenuPlanItemList(Meal currentMeal, List<MenuPlanItem> list, Quantity q) {
        mpi = new MenuPlanItem(currentMeal, q);
        list.add(mpi);

    }

   
    public MenuPlan createMenuPlan(List<MenuPlanItem> lista, Menu m) {

        menuplan = new MenuPlan(lista, m);
              
        return menuplan;
    }

    public void save(MenuPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        
        mpr.save(mp);
    }

    public void editMenuPlan(int quantity) {
        MenuPlan mp = mpr.getActiveMenuPlan();
   
        for (MenuPlanItem menu_plan_item:mp.getMenuPlanItemList()) {
            menu_plan_item.getQuantityNumber().setQuantity(quantity);
        }

    }
}