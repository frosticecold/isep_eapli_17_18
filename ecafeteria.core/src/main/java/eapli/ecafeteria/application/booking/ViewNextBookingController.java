/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author João Rocha 1161838
 */
public class ViewNextBookingController implements Controller{
    
    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();
    
//    public Booking getNextBooking(){
//        return bookingRepo.findNextBooking();
//    }
}
