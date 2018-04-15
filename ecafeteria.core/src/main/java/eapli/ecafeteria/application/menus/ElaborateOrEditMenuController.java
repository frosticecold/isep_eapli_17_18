/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;
import eapli.framework.date.DateEAPLI;
import eapli.framework.util.DateTime;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ElaborateOrEditMenuController implements Controller {

    /**
     * Repositories
     */
    private final MenuRepository menurepo;
    private final DishRepository dishrepo;
    private final DishTypeRepository dishtyperepo;

    /**
     * Member variables
     */
    private Menu m_menu;
    private Meal m_meal;
    private Iterable<DishType> listOfDishTypes;

    /**
     * FORCE dd-MM-yyyy FORMAT
     */
    public static final String PERIOD_VALID_FORMAT = DateTime.DEFAULT_SIMPLE_DATA_FORMAT;

    public ElaborateOrEditMenuController() {
        RepositoryFactory repositories = PersistenceContext.repositories();
        menurepo = repositories.menus();
        dishrepo = repositories.dishes();
        dishtyperepo = repositories.dishTypes();
    }

    public Menu createOrFindMenu(String initialDate, String finalDate, String simpledataformat) {
        Menu m = findMenu(initialDate, finalDate, simpledataformat);
        if (m == null) {
            try {
                m_menu = new Menu(initialDate, finalDate);
            } catch (IllegalArgumentException | ParseException ex) {
                Logger.getLogger(ElaborateOrEditMenuController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            m_menu = m;
        }
        return m_menu;
    }

    private Menu findMenu(String initialDate, String finalDate, String simpledataformat) {
        String validformat;
        if (simpledataformat == null || simpledataformat.isEmpty()) {
            validformat = PERIOD_VALID_FORMAT;
        } else {
            validformat = simpledataformat;
        }
        DateEAPLI dstart, dend;
        try {
            dstart = new DateEAPLI(initialDate, validformat);
            dend = new DateEAPLI(finalDate, validformat);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ElaborateOrEditMenuController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        Calendar calendar_start = dstart.getStartingDayOfWeek().toCalendar();
        Calendar calendar_end = dend.getEndingDayOfWeek().toCalendar();
        Optional<Menu> opt = menurepo.findMenuWithinPeriod(calendar_start, calendar_end);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    private Iterable<DishType> getDishTypes() {
        listOfDishTypes = dishtyperepo.activeDishTypes();
        return listOfDishTypes;
    }

}
