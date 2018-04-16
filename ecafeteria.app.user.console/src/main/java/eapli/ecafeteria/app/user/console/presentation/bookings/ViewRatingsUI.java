/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ViewRatingsController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsUI extends AbstractUI {
    
    private final ViewRatingsController controller = new ViewRatingsController();
    
    @Override
    protected boolean doShow() {
        for (Booking booking : controller.listServedBookings()) {
            System.out.println("Booking ID: " + booking.getIdBooking() 
                              + "\n"
                              + "Meal: " + booking.getMeal().toString()
                              + "\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
