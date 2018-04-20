/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.booking.ViewNextBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import javax.persistence.NoResultException;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewNextBookingUI extends AbstractUI {

    private ViewNextBookingController controller = null;
    private Booking nextBooking;

    public ViewNextBookingUI() {
        try {
            controller = new ViewNextBookingController();
            this.nextBooking = controller.getNextBooking();
        } catch(NoResultException ex) {
            System.out.println("\nUser does not have a valid ID! Could not retrieve next booking.");
        }
    }

    @Override
    protected boolean doShow() {
        if (nextBooking != null) {
            System.out.println("Next Booking: " + nextBooking);
            return true;
        } else {
            System.out.println("No next bookings where found");
            return false;

        }
    }

    @Override
    public String headline() {
        return "User's Next Booking";
    }

}
