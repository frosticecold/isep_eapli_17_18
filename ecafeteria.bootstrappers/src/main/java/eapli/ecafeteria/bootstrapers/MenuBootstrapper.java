/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class MenuBootstrapper implements Action{

    @Override
    public boolean execute() {
        final MenuRepository menurepo = PersistenceContext.repositories().menus();
        return true;
    }
    
}
