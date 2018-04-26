/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.dishes.ChangeDishController;
import java.util.logging.Logger;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.AlergenRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;

/**
 *
 * @author mcn
 */
public class DishBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType vegie = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_VEGIE).get();
        final DishType fish = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_FISH).get();
        final DishType meat = dishTypeRepo.findByAcronym(TestDataConstants.DISH_TYPE_MEAT).get();
        
        register(vegie, TestDataConstants.DISH_NAME_TOFU_GRELHADO, 140, 1, 2.99);
        register(vegie, TestDataConstants.DISH_NAME_LENTILHAS_SALTEADAS, 180, 1, 2.85);
        register(fish, TestDataConstants.DISH_NAME_BACALHAU_A_BRAZ, 250, 2, 3.99);
        register(fish, TestDataConstants.DISH_NAME_LAGOSTA_SUADA, 230, 2, 24.99);
        register(meat, TestDataConstants.DISH_NAME_PICANHA, 375, 2, 4.99);
        register(meat, TestDataConstants.DISH_NAME_COSTELETA_A_SALSICHEIRO, 475, 2, 3.99);
        final DishRepository dishRepo=PersistenceContext.repositories().dishes();
        final Dish tofu = dishRepo.findByName(Designation.valueOf(TestDataConstants.DISH_NAME_TOFU_GRELHADO)).get();
        final Dish lentilhas = dishRepo.findByName(Designation.valueOf(TestDataConstants.DISH_NAME_LENTILHAS_SALTEADAS)).get();
        final AlergenRepository alergenRepository=PersistenceContext.repositories().alergens();
        final Alergen soja = alergenRepository.findOne(Designation.valueOf(TestDataConstants.ALLERGEN_CRUSTACEOS)).get();
        final Alergen molusco = alergenRepository.findOne(Designation.valueOf(TestDataConstants.ALLERGEN_MOLUSCO)).get();
        addAlergen(tofu,soja);
        addAlergen(lentilhas,molusco);
        return true;
    }

    /**
     *
     */
    private void register(DishType dishType, String description, int cal, int salt, double price) {
        final RegisterDishController controller = new RegisterDishController();
        try {
            controller.registerDish(dishType, description, cal, salt, price);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
    
    private void addAlergen(Dish dish,Alergen alergen){
        final ChangeDishController controller= new ChangeDishController();
        try {
            controller.addAlergen(dish, alergen);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
