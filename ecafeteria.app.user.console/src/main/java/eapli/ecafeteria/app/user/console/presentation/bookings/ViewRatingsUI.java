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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsUI extends AbstractUI {

    private final ViewRatingsController controller = new ViewRatingsController();

    @Override
    protected boolean doShow() {
        int option = -1;
        ArrayList<Booking> bookings = (ArrayList<Booking>) controller.bookings();

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings!\n");
        } else {
            do {
                for (Booking booking : bookings) {
                    System.out.println("Booking ID: " + booking.getIdBooking()
                            + "\n"
                            + "Meal: " + booking.getMeal().toString()
                            + "\n");
                }

                final Long id = Console.readLong("» Please enter the Booking ID: ");

                controller.setMeal(id);

                if (controller.ratingsFromMeal() == null) {

                    System.out.println("\n-----------------------");
                    System.out.println("\n»»»»»» Warning!\n» Booking ID is incorrect!\n");

                } else {

                    System.out.println("Booking ID: " + id);

                    Iterator<Rating> it = controller.ratingsFromMeal().iterator();

                    System.out.println("\n-----------------------");
                    if (!it.hasNext()) {
                        System.out.println("\n»»»»»» Warning!\n» There are no ratings for this meal\n");
                    } else {
                        while (it.hasNext()) {
                            System.out.println(it.next());
                        }
                    }
                }

                System.out.println("\n-----------------------");

                option = Console.readInteger("\n» Do you wish to see another booking?"
                        + "\n» Enter any number to continue, 0 to terminate: ");

                System.out.println("\n-----------------------");
                controller.clearSelection();
            } while (option != 0);
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
