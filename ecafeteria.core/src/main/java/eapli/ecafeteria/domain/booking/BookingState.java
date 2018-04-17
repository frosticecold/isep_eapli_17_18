/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author jpfr8
 */
public class BookingState {
    /**
     * Actual booking state
     */
    private BookingStates actualBookingState;

    /**
     * Constructor
     */
    public BookingState() {
        actualBookingState = BookingStates.BOOKED;
    }
        
    /**
     * Gives the actual state of the booking
     * 
     * @return booking state
     */
    public BookingStates actualState(){
        return actualBookingState;
    }
    
    /**
     * Informs if booking is cancelable
     * 
     * @return true if possible
     */
    public boolean isBookingStateCancelable(){
        return actualBookingState == BookingStates.BOOKED;
    }
    
    /**
     * Changes booking state to served if possible
     * 
     * @return true if changed
     */
    public boolean changeToServed(){
        if(actualBookingState == BookingStates.BOOKED){
            actualBookingState = BookingStates.SERVED;
            return true;
        }
        
        return false;
    }
    
    /**
     * Changes booking state to not served if possible
     * 
     * @return true if changed
     */
    public boolean changeToNotServed(){
        if(actualBookingState == BookingStates.BOOKED){
            actualBookingState = BookingStates.NOT_SERVED;
            return true;
        }
        
        return false;
    }
    
    /**
     * Changes booking state to canceled if possible
     * 
     * @return true if changed
     */
    public boolean changeToCanceled(){
        if(actualBookingState == BookingStates.BOOKED){
            actualBookingState = BookingStates.CANCELED;
            return true;
        }
        
        return false;
    }
}
