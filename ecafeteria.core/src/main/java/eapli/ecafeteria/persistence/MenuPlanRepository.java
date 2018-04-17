/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.framework.persistence.repositories.DataRepository;
import java.io.Serializable;


public interface MenuPlanRepository extends DataRepository<MenuPlan,Long> {
    
}
