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
        
        final Meal meal1 = mealRepo.findOne((long)25).get();
        final Meal meal2 = mealRepo.findOne((long)26).get();
        final Meal meal3 = mealRepo.findOne((long)27).get();
        final Meal meal4 = mealRepo.findOne((long)28).get();
        final Meal meal5 = mealRepo.findOne((long)29).get();
        final Meal meal6 = mealRepo.findOne((long)30).get();
        final Meal meal7 = mealRepo.findOne((long)31).get();
        final Meal meal8 = mealRepo.findOne((long)32).get();
        final Meal meal9 = mealRepo.findOne((long)33).get();
        final Meal meal10 = mealRepo.findOne((long)34).get();
        final Meal meal11 = mealRepo.findOne((long)35).get();
        final Meal meal12 = mealRepo.findOne((long)36).get();
        final Meal meal13 = mealRepo.findOne((long)37).get();
        final Meal meal14 = mealRepo.findOne((long)38).get();
        final Meal meal15 = mealRepo.findOne((long)39).get();
        final Meal meal16 = mealRepo.findOne((long)40).get();
        final Meal meal17 = mealRepo.findOne((long)41).get();
        final Meal meal18 = mealRepo.findOne((long)42).get();
        final Meal meal19 = mealRepo.findOne((long)43).get();
        final Meal meal20 = mealRepo.findOne((long)44).get();
        final Meal meal21 = mealRepo.findOne((long)45).get();
        final Meal meal22 = mealRepo.findOne((long)46).get();
        final Meal meal23 = mealRepo.findOne((long)47).get();
        final Meal meal24 = mealRepo.findOne((long)48).get();
        final Meal meal25 = mealRepo.findOne((long)49).get();
        final Meal meal26 = mealRepo.findOne((long)50).get();
        final Meal meal27 = mealRepo.findOne((long)51).get();
        final Meal meal28 = mealRepo.findOne((long)52).get();
        final Meal meal29 = mealRepo.findOne((long)53).get();
        final Meal meal30 = mealRepo.findOne((long)54).get();
        final Meal meal31 = mealRepo.findOne((long)55).get();
        final Meal meal32 = mealRepo.findOne((long)56).get();
        final Meal meal33 = mealRepo.findOne((long)57).get();
        final Meal meal34 = mealRepo.findOne((long)58).get();
        final Meal meal35 = mealRepo.findOne((long)59).get();
        final Meal meal36 = mealRepo.findOne((long)60).get();
        final Meal meal37 = mealRepo.findOne((long)61).get();
        final Meal meal38 = mealRepo.findOne((long)62).get();
        final Meal meal39 = mealRepo.findOne((long)63).get();
        final Meal meal40 = mealRepo.findOne((long)64).get();
        final Meal meal41 = mealRepo.findOne((long)65).get();
        final Meal meal42 = mealRepo.findOne((long)66).get();
        
        Calendar start = DateTime.parseDate("01-07-2018");
        Calendar end = DateTime.parseDate("07-07-2018");
        Menu menu = new Menu(start, end);
        register(menu);
        start = DateTime.parseDate("06-05-2018");
        end = DateTime.parseDate("12-05-2018");
        menu = new Menu(start, end);
        menu.addMeal(meal1);
        menu.addMeal(meal2);
        menu.addMeal(meal3);
        menu.addMeal(meal4);
        menu.addMeal(meal5);
        menu.addMeal(meal6);
        menu.addMeal(meal7);
        menu.addMeal(meal8);
        menu.addMeal(meal9);
        menu.addMeal(meal10);
        menu.addMeal(meal11);
        menu.addMeal(meal12);
        menu.addMeal(meal13);
        menu.addMeal(meal14);
        menu.addMeal(meal15);
        menu.addMeal(meal16);
        menu.addMeal(meal17);
        menu.addMeal(meal18);
        menu.addMeal(meal19);
        menu.addMeal(meal20);
        menu.addMeal(meal21);
        menu.addMeal(meal22);
        menu.addMeal(meal23);
        menu.addMeal(meal24);
        menu.addMeal(meal25);
        menu.addMeal(meal26);
        menu.addMeal(meal27);
        menu.addMeal(meal28);
        menu.addMeal(meal29);
        menu.addMeal(meal30);
        menu.addMeal(meal31);
        menu.addMeal(meal32);
        menu.addMeal(meal33);
        menu.addMeal(meal34);
        menu.addMeal(meal35);
        menu.addMeal(meal36);
        menu.addMeal(meal37);
        menu.addMeal(meal38);
        menu.addMeal(meal39);
        menu.addMeal(meal40);
        menu.addMeal(meal41);
        menu.addMeal(meal42);
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
