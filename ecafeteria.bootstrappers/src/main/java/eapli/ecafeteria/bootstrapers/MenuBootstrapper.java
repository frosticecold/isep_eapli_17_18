/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menus.ElaborateOrEditMenuController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class MenuBootstrapper implements Action {

    @Override
    public boolean execute() {
        final MenuRepository menuRepo = PersistenceContext.repositories().menus();
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType vegie = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_VEGIE).get();
        final MealRepository mealRepo = PersistenceContext.repositories().meals();
        
        final Meal meal = mealRepo.findMealByDishID(Designation.valueOf("tofu grelhado")).get();
        
        Calendar start = DateTime.parseDate("01-07-2018");
        Calendar end = DateTime.parseDate("07-07-2018");
        Calendar mealDay = DateTime.parseDate("01-07-2018");
        Menu menu = new Menu(start, end);
        register(menu);
        start = DateTime.parseDate("06-05-2018");
        end = DateTime.parseDate("12-05-2018");
        menu = new Menu(start, end);
//        DishType dishType = new DishType("Italian", "Awesome!");
//        Designation name = Designation.valueOf("Lasanha");
//        Money price = Money.euros(10);
//        Alergen alergen = new Alergen("peanuts");
//        Dish dish = new Dish(vegie, name, price, alergen);
//        Meal meal = new Meal(dish, MealType.LUNCH, mealDay);
        menu.addMeal(meal);
        register(menu);
        return true;
    }

    private void register(Menu menu) {
        final ElaborateOrEditMenuController controller = new ElaborateOrEditMenuController();
        try {
            controller.saveMenu(menu);

        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }

    }
}
