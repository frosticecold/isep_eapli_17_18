/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;


public class CloseMenuPlanController {
    
    private MenuPlan mp;
    
    private final MenuPlanRepository mpr=PersistenceContext.repositories().menuPlan();
    
    public MenuPlan getMenuPlan(){
        mp=mpr.getActiveMenuPlan();
        return mp;
    }
    
    public boolean validate(MenuPlan mp){
        List<MenuPlanItem>lista=mp.getMenuPlanItemList();
        
        for (MenuPlanItem mpi:lista) {
            
            if(mpi.getQuantityNumber()==null){
                return false;
            }
            mp.getSelectedMenu();
            
//            if()
            
        }
        
        return true;
    }
}
