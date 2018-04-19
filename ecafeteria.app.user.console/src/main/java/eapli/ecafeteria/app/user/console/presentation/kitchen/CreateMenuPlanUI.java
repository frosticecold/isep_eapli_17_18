/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.kitchen;

import eapli.ecafeteria.application.menuplan.CreateMenuPlanController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.framework.presentation.console.AbstractUI;


public class CreateMenuPlanUI extends AbstractUI {

    private CreateMenuPlanController controller = new CreateMenuPlanController();
     
    @Override
    protected boolean doShow() {
        
        Menu m=controller.getCurrentMenu();
        
        
        
        return true;
    }

    @Override
    public String headline() {
        return "Create a Plan for this week Menu";
    }
    
}
