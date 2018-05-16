/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.RatingCommentNotificationController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingCommentNotificationUI extends CafeteriaUserBaseUI {

    private RatingCommentNotificationController controller = new RatingCommentNotificationController();

    @Override
    protected boolean doShow() {
        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
