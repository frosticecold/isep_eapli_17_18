/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.List;


public class CloseMenuPlanController {
    
    private List<MenuPlan> lmp;
    
    private final MenuPlanRepository mpr=PersistenceContext.repositories().menuPlan();
    
    private final MenuPlanItemRepository mpir=PersistenceContext.repositories().menuPlanItem();
    
    public List<MenuPlan> getMenuPlans(){
        lmp=mpr.getActiveMenuPlans();
        return lmp;
    }
    
    public boolean validate(MenuPlan mp){
        
        List<MenuPlanItem> lista = mpir.getMenuPlanItemFromMenuPlan(mp);
        for (MenuPlanItem mpi: lista) {
            if(mpi.getQuantityNumber().getQuantity()<0){
                return false;
            }else{
                if(mp.getSelectedMenu().period().getWorkingDays().get(0).getTimeInMillis()-(DateTime.now().getTimeInMillis())<=172800000){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return true;
    }
    
    public void saveMenuPlan(MenuPlan mp) throws DataConcurrencyException, DataIntegrityViolationException{
         AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
         mpr.save(mp) ;
    }
    
    public void changeStatus(MenuPlan mp ){
        mp.setClosed(true);
    }
}

