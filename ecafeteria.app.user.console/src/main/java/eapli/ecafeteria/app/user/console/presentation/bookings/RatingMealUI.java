/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.RatingMealController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingMealUI extends CafeteriaUserBaseUI {

    private final RatingMealController controller = new RatingMealController();

    @Override
    protected boolean doShow() {
        final List<Booking> bookings = controller.showBookings();

        if (!bookings.iterator().hasNext()) {
            System.out.println("There are no registered consumed bookings");
            return false;
        }

        // Show list with bookings
        SelectWidget<Booking> bookingWidget = new SelectWidget<>("Select a booking for the rating", controller.showBookings());
        bookingWidget.show();

        //Select booking
        Booking selectedBooking = bookingWidget.selectedElement();

        if (selectedBooking == null) {
            //Exit option
            return false;
        }

        int rating = 0;
        rRating();
        String comment = rComme();
        //String comment = Console.readLine("Leave a reply (optional) :");

        // add Rating
        try {
            controller.addRating(selectedBooking, comment);
            System.out.println("Booking successfully rated \n");
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Unable to register rating. Try again later");
        }
        return false;
    }

    @Override
    public String headline() {
        return "*** Rating of the meal ***";
    }

    public void rRating() {
        int rating = Console.readInteger("Rate the meal from 1 to 5 :");
        if (!controller.readRating(rating)) {
            rRating();
        }

    }

    public String rComme() {
        String comment = "";
        if (controller.readComment()) {
            do {
                comment = Console.readLine("Please leave your comment");
            } while (comment == null);
        }
        return comment;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
