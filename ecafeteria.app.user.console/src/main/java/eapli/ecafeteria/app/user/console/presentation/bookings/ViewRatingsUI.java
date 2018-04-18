/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ViewRatingsController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsUI extends AbstractUI {
    
    private final ViewRatingsController controller = new ViewRatingsController();
    
    @Override
    protected boolean doShow() {
        for (Booking booking : controller.bookings()) {
            System.out.println("Booking ID: " + booking.getIdBooking() 
                              + "\n"
                              + "Meal: " + booking.getMeal().toString()
                              + "\n");
        }
        
        final Long id = Console.readLong("Please enter the Booking ID: ");
        
        controller.setMeal(id);
        
        for (Rating rating : controller.ratingsFromMeal()) {
            System.out.println(rating.toString());
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
