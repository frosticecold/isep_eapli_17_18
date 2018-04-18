/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.booking.CancelBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author David Camelo 1161294@isep.ipp.pt
 */
public class CancelBookingUI extends AbstractUI{

    /**
     * Controller !missing user!
     */
    private CancelBookingController controller = new CancelBookingController();    
    
    
    @Override
    protected boolean doShow() {
        // Show list with bookings
        SelectWidget<List<Booking>> selectWidget = 
                new SelectWidget("Select a booking to cancel", controller.showBookings());
        
        // Select booking
        controller.selectBooking(selectWidget.selectedOption());
        
        if(controller.informBookingState()){
            // Show information
            System.out.println("Booking is ready to be canceled");
            System.out.println(controller.informRefundValue());
            
            // Cancel & Confirm
            System.out.println("Would you like to cancel the booking? [y/n]");
            String answer = null;
            do {                
                Console.readLine(answer);
            } while (!answer.equals("y") && !answer.equals("n"));
        
            if(answer.equals("n")){
                System.out.println("Cancel operation failed.");
            }else{
                // Informs success
                try {
                    if(controller.confirmCancelation()){
                        System.out.println("Success. Booking was removed.");
                    }else{
                        System.out.println("Error occured. Not possible to remove "
                                + "booking. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error while saving. Please check your connection.");
                }
            }
        }else{
            System.out.println("It is not possible to cancel this booking.");
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Cancel booking";
    }
    
    
    
}
