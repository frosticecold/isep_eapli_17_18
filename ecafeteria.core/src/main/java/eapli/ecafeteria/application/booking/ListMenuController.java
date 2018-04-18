/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.menu.Menu;
import static eapli.framework.util.DateTime.beginningOfWeek;
import static eapli.framework.util.DateTime.currentWeekNumber;
import static eapli.framework.util.DateTime.endOfWeek;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Telmo
 */
public class ListMenuController {
    private final ListMenuService svc = new ListMenuService();
    
    public Optional<Menu> listMenuCurrentWeek(){
        //PersistenceContext.repositories().menus().findMenuWithinPeriod(startDate, endDate);
        return svc.findMenuWithinPeriod(beginningOfWeek(Calendar.YEAR,currentWeekNumber()), endOfWeek(Calendar.YEAR,currentWeekNumber()));
    }
}
