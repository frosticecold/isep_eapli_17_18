/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author David Camelo 1161294@isep.ipp.pt
 */
public class CancelBookingController {

    /**
     * Bookings repository
     */
    private BookingRepository bookingRepository = null;
    
    /**
     * List with all bookings of the user
     */
    private List<Booking> bookings = null;
    
    /**
     * Selected booking
     */
    private Booking selectedBooking = null;

    /**
     * Constructor
     * 
     * @param user current user 
     */
    public CancelBookingController(CafeteriaUser user) {
        // Load Bookings from repository    
        bookingRepository = PersistenceContext.repositories().booking();
        bookings = bookingRepository.findBookingsByCafeteriaUser(user);
    }
    
    /**
     * Shows booking that are in the booked state
     * 
     * @return list of bookings
     */
    public List<Booking> showBookings(){
        return bookings;
    }
    
    /**
     * Selects booking from booking list
     * 
     * @param idBooking position of booking in list 
     */
    public void selectBooking(int idBooking){
        if(0 <= idBooking && idBooking < bookings.size())
            this.selectedBooking = bookings.get(idBooking);
        else
            throw new IllegalArgumentException("Booking id " + 
                    idBooking + "is not valid!");
    }
    
    /**
     * Informs user of the possibility to cancel the booking
     * 
     * @return true if changed
     */
    public boolean informBookingState(){
        return selectedBooking.getBookingState().changeToCanceled();
    }
    
    /**
     * 
     * @return 
     */
    public boolean confirmCancelation(){
        // Persist changes in DB
        // 
        return false;
    }
}
