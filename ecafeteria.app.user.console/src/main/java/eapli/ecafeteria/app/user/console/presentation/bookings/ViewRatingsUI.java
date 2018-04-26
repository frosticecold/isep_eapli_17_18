/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.ViewRatingsController;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.presentation.console.AbstractUI;
import java.util.ArrayList;
import javax.persistence.NoResultException;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class ViewRatingsUI extends AbstractUI implements ViewNextBookingInterface {

    private ViewRatingsController controller = null;

    @Override
    protected boolean doShow() {

        try {
            controller = new ViewRatingsController();
            ArrayList<Rating> ratings = (ArrayList<Rating>) controller.ratings();

            if (ratings.isEmpty()) {
                System.out.println("»» An error has occurreed!\n There are no ratings!\n");
                return false;
            } else {
                for (Rating rating : ratings) {
                    System.out.println("---------------------------------------------");
                    System.out.println(rating.toString());
                    System.out.print("---------------------------------------------\n");
                }
            }

        } catch (NoResultException ex) {
            System.out.println("\n»» An internal error has occurreed!\n There are no ratings, specified user has an invalid ID.\n");
            return false;
        } catch (NullPointerException ex) {
            System.out.println("\n»» An internal error has occurreed! Ratings are null.\n");
            return false;
        }

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
