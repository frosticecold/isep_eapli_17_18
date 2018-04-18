/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ListMenuController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Optional;

/**
 *
 * @author Telmo
 */
public class ListMenuUI extends AbstractUI{
    
    private final ListMenuController controller = new ListMenuController();
    
    @Override
    protected boolean doShow() {
        Optional<eapli.ecafeteria.domain.menu.Menu> menuOfCurrentWeek = controller.listMenuCurrentWeek();
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
