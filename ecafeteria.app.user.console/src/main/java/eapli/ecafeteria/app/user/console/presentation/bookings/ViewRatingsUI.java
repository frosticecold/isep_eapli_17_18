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
import javax.persistence.NoResultException;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsUI extends AbstractUI  implements ViewNextBookingInterface {

    private ViewRatingsController controller = null;

    @Override
    protected boolean doShow() {

        try {
            controller = new ViewRatingsController();
            
            int option = -1;
            ArrayList<Rating> ratings = (ArrayList<Rating>) controller.ratings();

            if (ratings.isEmpty()) {
                System.out.println("»» An error has occurreed!\n There are no ratings!\n");
            } else {
                for (Rating rating : ratings) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Meal » " + rating.meal().mealtype().toString() + " \nDish » " + rating.meal().dish().name().toString() + "\n"
                            + "Rating: " + rating.toString());
                    System.out.print("---------------------------------------------\n");
                }
            }

        } catch (NoResultException ex) {
            System.out.println("\n»» An error has occurreed!\n There are no ratings, specified user has an invalid ID\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
