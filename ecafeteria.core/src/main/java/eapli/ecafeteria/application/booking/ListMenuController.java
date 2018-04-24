/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author Telmo
 */
public class ListMenuController implements Controller{
    public ListMenuController(){
        
    }
    
    public List<Meal> listMenuCurrentWeek(){
        return MenuService.findMenuWithinPeriod();
    }
}
