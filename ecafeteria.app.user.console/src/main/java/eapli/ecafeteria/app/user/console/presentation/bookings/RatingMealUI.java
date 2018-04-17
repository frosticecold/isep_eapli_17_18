/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.RatingMealController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingMealUI extends AbstractUI {

    private final RatingMealController controller = new RatingMealController();

    @Override
    protected boolean doShow() {
        final List<Booking> bookings = controller.findBookings();

        if (!bookings.iterator().hasNext()) {
            System.out.println("There are no registered consumed bookings");
            return false;
        }
        int rating = Console.readInteger("Rate the meal from 1 to 5 :");
        String comment = Console.readLine("Leave a reply (optional) :");

        try {
            controller.addRating(rating, comment);
            System.out.println("Booking successfully rated \n");
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Unable to register rating. Try again later");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Rating of the meal";
    }

}
