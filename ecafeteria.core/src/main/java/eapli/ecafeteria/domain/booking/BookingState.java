/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
@Embeddable
public class BookingState implements ValueObject, Serializable {
    
    private static final long serialVersionUID =10L;
    
    public enum BookingStates {
        @Column(name = "BOOKED") BOOKED{
            @Override
            public String toString(){
                return "BOOKED";
            }
        },
        @Column(name = "SERVED") SERVED{
            @Override
            public String toString(){
                return "SERVED";
            }
        },
        @Column(name = "NOT_SERVED") NOT_SERVED{
            @Override
            public String toString(){
                return "NOT_SERVED";
            }
        },
        @Column(name = "CANCELLED") CANCELLED{
            @Override
            public String toString(){
                return "CANCELLED";
            }
        }
    }
    
    /**
     * Actual booking state
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "bookingstate")
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
            actualBookingState = BookingStates.CANCELLED;
            return true;
        }
        
        return false;
    }

    public BookingStates getActualBookingState() {
        return actualBookingState;
    }

    public void setActualBookingState(BookingStates actualBookingState) {
        this.actualBookingState = actualBookingState;
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        BookingState bookingState = (BookingState) other;
        return actualBookingState.equals(bookingState.getActualBookingState());
    }

    
    
    
}
