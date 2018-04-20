/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.beginningOfWeek;
import static eapli.framework.util.DateTime.currentWeekNumber;
import static eapli.framework.util.DateTime.endOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


public class CreateMenuPlanController implements Controller {
    
     private final MenuService svc = new MenuService();
     
     private final MenuPlanRepository mpr=PersistenceContext.repositories().menuPlan();
     
     private Menu m;
     
     private MenuPlan menuplan;
     
     private MenuPlanItem mpi;
     
     private Quantity q;
         
     //metodo que vai buscar menus da semana atual
     public Menu getCurrentMenu(){
         m=svc.findMenuWithinPeriod(beginningOfWeek(Calendar.YEAR,currentWeekNumber()+1), endOfWeek(Calendar.YEAR,currentWeekNumber()+1)).get();
        return m;
        
     }

     //metodo que vai a cada refeicao preencher com o numero de pratos
    public MenuPlan setNumberDishesForMeal(int quantity){
        
        List<MenuPlanItem> list=new ArrayList<>();
        
        Calendar bDay = DateTime.beginningOfWeek(Calendar.YEAR, currentWeekNumber());
        for(int i=0; i<7;i++){
            Iterable<Meal> meals = m.getMealsByDay(bDay);
            for(Meal currentMeal : meals){
               q.setQuantity(quantity);
               mpi=new MenuPlanItem(currentMeal,q);  
               list.add(mpi);
            }
            
            bDay.add(Calendar.DAY_OF_MONTH, 1);
        
        }
        
        menuplan=new MenuPlan(list, m);
        
        return menuplan;
    }
    
     //metodo que poe isso na base de dados
    public void save(MenuPlan mp) throws DataConcurrencyException, DataIntegrityViolationException{
      
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);      
        mpr.save(mp);
    }
}
