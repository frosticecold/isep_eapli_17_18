/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.booking.ViewNextBookingController;
import eapli.ecafeteria.domain.booking.Booking;

/**
 *
 * @author Joao Rocha 1161838
 */
public interface ViewNextBookingInterface {
    
    default void showNextBooking(){
        ViewNextBookingController controller = new ViewNextBookingController();
        Booking nextBooking = controller.getNextBooking();
        System.out.println("+===============================================+\n");
        if(nextBooking == null){
            System.out.println("NO BOOKINGS WHERE FOUND \n");
        }
        else{
            System.out.println("NEXT BOOKING: \n" +  nextBooking);
        }
        System.out.println("+===============================================+\n");
    }
    
}
