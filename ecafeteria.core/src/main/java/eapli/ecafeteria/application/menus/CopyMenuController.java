/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class CopyMenuController implements Controller {

    private final MenuRepository menurepo = PersistenceContext.repositories().menus();

    public Iterable<Menu> getAvailableMenus() {
        return menurepo.findAll();
    }
}
