/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ListMenuController;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.currentWeekNumber;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Telmo
 */
public class ListMenuUI extends AbstractUI implements ViewNextBookingInterface{

    private final ListMenuController controller = new ListMenuController();

    @Override
    protected boolean doShow() {
        try {
            showNextBooking();
            List<Meal> menuOfCurrentWeek = controller.listMenuCurrentWeek();
            for(Meal m : menuOfCurrentWeek){
                System.out.println(m.toString());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Não tem menus para esta semana");
            return false;
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
