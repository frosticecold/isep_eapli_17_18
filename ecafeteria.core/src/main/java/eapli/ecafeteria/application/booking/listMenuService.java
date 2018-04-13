/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class listMenuService {
    
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
}
