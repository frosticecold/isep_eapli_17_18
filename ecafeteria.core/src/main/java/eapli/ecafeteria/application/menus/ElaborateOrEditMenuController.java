/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.Period;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ElaborateOrEditMenuController implements Controller {

    /**
     * Repositories
     */
    private MenuRepository menurepo;
    private DishRepository dishrepo;
    private DishTypeRepository dishtyperepo;

    /**
     * Member variables
     */
    private Menu m_menu;
    private Meal m_meal;

    /**
     * FORCE FORMAT
     */
    public static final String PERIOD_VALID_FORMAT = Period.PERIOD_VALID_DATE_FORMAT;

    public ElaborateOrEditMenuController() {
        RepositoryFactory repositories = PersistenceContext.repositories();
        menurepo = repositories.menus();
        dishrepo = repositories.dishes();
        dishtyperepo = repositories.dishTypes();
    }

    public void createOrFindMenu(String initialDate, String finalDate) {
        menurepo.findMenuWithinPeriod(initialDate, finalDate, PERIOD_VALID_FORMAT);
        if (false) {
            m_menu = new Menu(initialDate, finalDate);
        }
    }
}
