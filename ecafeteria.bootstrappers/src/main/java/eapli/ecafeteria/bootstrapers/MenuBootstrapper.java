/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menus.ElaborateOrEditMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
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
        final MenuRepository menurepo = PersistenceContext.repositories().menus();
        Calendar start = DateTime.parseDate("01-07-2018");
        Calendar end = DateTime.parseDate("07-07-2018");
        Menu menu = new Menu(start, end);
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
