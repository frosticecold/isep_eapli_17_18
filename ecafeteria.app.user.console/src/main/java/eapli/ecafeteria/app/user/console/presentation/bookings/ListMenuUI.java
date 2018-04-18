/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ListMenuController;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.currentWeekNumber;
import java.util.Calendar;


/**
 *
 * @author Telmo
 */
public class ListMenuUI extends AbstractUI{
    
    private final ListMenuController controller = new ListMenuController();
    
    @Override
    protected boolean doShow() {
        eapli.ecafeteria.domain.menu.Menu menuOfCurrentWeek = controller.listMenuCurrentWeek().get();
        Calendar bDay = DateTime.beginningOfWeek(Calendar.YEAR, currentWeekNumber());
        for(int i=0; i<7;i++){
            Iterable<Meal> meals = menuOfCurrentWeek.getMealsByDay(bDay);
            for(Meal m : meals){
                m.toString();
            }
            
            bDay.add(Calendar.DAY_OF_MONTH, 1);
            
        }
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
