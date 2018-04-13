/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu_plan;

import eapli.ecafeteria.domain.menu.Menu;
import java.util.List;

public class MenuPlan {
    
    private List<MenuPlanItem> menuPlanItemList;
    private Menu selectedMenu;
    private boolean closed;

    public MenuPlan(List<MenuPlanItem> menuPlanItemList, Menu menu) {
        this.menuPlanItemList = menuPlanItemList;
        this.selectedMenu = menu;
        closed=false;
    }
    
    
    
    
    
}
