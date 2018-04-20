/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    Iterable<Menu> listValidMenus();

    /**
     * Method that returns if exists a menu within a given period
     *
     *
     * @param startDate Starting date
     * @param endDate Ending date
     * @return Menu if found, null ifnot
     */
    Optional<Menu> findMenuWithinPeriod(final Calendar startDate, final Calendar endDate);

}
