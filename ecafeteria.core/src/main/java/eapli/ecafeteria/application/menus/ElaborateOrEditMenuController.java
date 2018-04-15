/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.dishes.Dish;
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
import java.util.Map;
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
    private Map<Integer, Calendar> mapOfWorkingDays;
    private Calendar selectedDay;

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
        getDishTypes();
    }

    /**
     * First method to execute
     *
     * @author Raúl Correia
     * @param initialDate String with initial Date
     * @param finalDate String with final Date
     * @param simpledataformat simple data format (dd-MM-yyyy)
     * @return Menu if exists or create a new one
     */
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

    /**
     * Returns the working days of a menu
     *
     * @param m
     * @return
     */
    public Map<Integer, Calendar> getMenuWorkingDays(Menu m) {
        if (m == null) {
            return null;
        }
        mapOfWorkingDays = m.getWorkWeekDays();
        return mapOfWorkingDays;
    }

    public Calendar selectDay(Integer dayIndex) {
        selectedDay = mapOfWorkingDays.get(dayIndex);
        return selectedDay;
    }

    public Calendar selectDay(Calendar cal) {
        selectedDay = cal;
        return selectedDay;
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

    public Iterable<DishType> getDishTypes() {
        if (listOfDishTypes == null) {
            listOfDishTypes = dishtyperepo.activeDishTypes();
        }
        return listOfDishTypes;
    }
    
    public Iterable<Dish> getDishesByDishType(DishType dishtype){
        return dishrepo.findByDishType(dishtype);
    }

}
