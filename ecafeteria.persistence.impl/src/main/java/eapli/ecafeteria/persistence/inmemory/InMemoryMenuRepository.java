/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class InMemoryMenuRepository extends InMemoryRepositoryWithLongPK<Menu> implements MenuRepository {

    @Override
    public Iterable<Menu> listValidMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Menu> findMenuWithinPeriod(final Calendar initialDate, final Calendar endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Iterable<Meal> listMealsPublishedMenu(Calendar date, MealType mealType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
}
