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
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
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

    private final MenuPlanItemRepository mpir = PersistenceContext.repositories().menuPlanItem();

    private List<Menu> lm;

    private MenuPlan menuplan;

    private MenuPlanItem mpi;

    private Quantity q;

    public List<Menu> getCurrentMenus() {

        lm = MenuService.findLatestMenus();
        return lm;
    }

    public MenuPlan getMenuPlanFromMenu(Menu m) {

        return mpr.getMenuPlanFromMenu(m);
    }

    public Iterable<Meal> mealsFromMenuByDay(Calendar bDay, Menu me) {
        return MenuService.getMealsFromMenuByGivenDay(me, bDay);
    }

    public Quantity insertQuantity(int quantity) {
        q = new Quantity(quantity);
        return q;
    }

    public MenuPlanItem createMenuPlanItem(MenuPlan mp,Meal currentMeal, Quantity q) {
        mpi = new MenuPlanItem(mp,currentMeal, q);
        return mpi;

    }

    public MenuPlan createMenuPlan(Menu m) {

        menuplan = new MenuPlan(m);

        return menuplan;
    }

    public MenuPlanItem saveMenuPlanItem(MenuPlanItem mpi) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        MenuPlanItem menupi=mpir.save(mpi);
        return menupi;
    }

    public MenuPlan saveMenuPlan(MenuPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        MenuPlan save = mpr.save(mp);
        return save;
    }

    public List<MenuPlan> getActiveMenuPlans() {
        return mpr.getActiveMenuPlans();
    }
    
    public List<MenuPlanItem> getMenuPlanItemsFromMenuPlan(MenuPlan mp){
         return mpir.getMenuPlanItemFromMenuPlan(mp);
    }
           
    

    public void editMenuPlan(int quantity, MenuPlanItem menu_plan_item) {

        menu_plan_item.getQuantityNumber().setQuantity(quantity);

    }
}
