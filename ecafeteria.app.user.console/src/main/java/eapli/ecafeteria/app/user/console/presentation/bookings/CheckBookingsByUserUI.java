/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckBookingsByUserController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.presentation.console.AbstractUI;
import java.util.ArrayList;
import javax.persistence.NoResultException;
import java.util.*;
/**
 *
 * @author 1160911 <Rafael Teixeira>
 */
public class CheckBookingsByUserUI extends AbstractUI
{

    private CheckBookingsByUserController controller = new CheckBookingsByUserController();

    @Override
    protected boolean doShow()
    {
        try
        {

            CafeteriaUser user = controller.getCurrentUser();
            List<Booking> bookings = controller.findBookingsByCafeteriaUser(user, new BookingState());

            if (bookings.isEmpty())
            {
                System.out.println("»» An error has occurreed!\n There are no bookings!!\n");
                return false;
            } else
            {
                for (Booking booked : bookings)
                {
                    System.out.println("---------------------------------------------");
                    System.out.println(booked.toString());
                    System.out.print("---------------------------------------------\n");
                }
            }

        } catch (NoResultException ex)
        {
            System.out.println("\n»» An internal error has occurreed!\n There are no bookings, specified user has an invalid ID.\n");
            return false;
        } catch (NullPointerException ex)
        {
            System.out.println("\n»» An internal error has occurreed! Bookings are null.\n");
            return false;
        }

        return true;
    }

    @Override
    public String headline()
    {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
