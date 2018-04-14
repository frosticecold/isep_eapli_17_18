/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.application.Controller;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ElaborateOrEditMenuController implements Controller {
    
    private MenuRepository menurepo;
    private DishRepository dishrepo;
    private DishTypeRepository dishtyperepo;
}
