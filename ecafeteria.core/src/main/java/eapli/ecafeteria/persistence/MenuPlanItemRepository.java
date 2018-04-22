/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author pedro
 */
public interface MenuPlanItemRepository extends DataRepository<MenuPlanItem,Long> {
    
}
