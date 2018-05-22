/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MFerreira
 */
public class MenuPlanItemBootstrapper implements Action{

    @Override
    public boolean execute() {
        
        final MenuPlanRepository menuplanRepo = PersistenceContext.repositories().menuPlan();
        final MealRepository mealRepo = PersistenceContext.repositories().meals();
        
        final Optional<MenuPlan> menuPlan1 = menuplanRepo.findOne(new Long(113));
        final Optional<Meal> meal1 = mealRepo.findOne(new Long(63));
        
        MenuPlanItem mpi1 = new MenuPlanItem(menuPlan1.get(), meal1.get(), new Quantity(6));
        
        final MenuPlanItemRepository mpiRepo = PersistenceContext.repositories().menuPlanItem();
        
        try {
            mpiRepo.save(mpi1);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(MenuPlanItemBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(MenuPlanItemBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
