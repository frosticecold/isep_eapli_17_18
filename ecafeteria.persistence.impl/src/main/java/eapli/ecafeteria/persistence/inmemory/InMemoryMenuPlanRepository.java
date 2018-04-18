/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;


public class InMemoryMenuPlanRepository extends InMemoryRepositoryWithLongPK<MenuPlan> implements MenuPlanRepository  {

    @Override
    public MenuPlan saveMenuPlan(MenuPlan menuplan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
