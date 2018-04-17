/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Version;
import eapli.ecafeteria.domain.cafeteriauser.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.framework.domain.money.Money;
import java.util.HashMap;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToOne;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    
    @EmbeddedId
    private int idBooking;
    @OneToOne
     private Meal meal; 
     private BookingState bookingState;
     private CafeteriaUser cafeteriaUser;
     
     
    public Booking(int idBooking, Meal meal, CafeteriaUser cafeteriauser) {
        this.idBooking = idBooking;
        this.meal = meal;
        this.bookingState = new BookingState();
        this.cafeteriaUser = cafeteriauser;
    }
     
     
    protected Booking() {
        // for ORM only
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }

        final Booking other = (Booking) o;
        return getIdBooking() == other.getIdBooking();
    }

    public int getIdBooking() {
        return idBooking;
    }

    public Meal getMeal() {
        return meal;
    }

    public BookingState getBookingState() {
        return bookingState;
    }

    public CafeteriaUser getCafeteriauser() {
        return cafeteriaUser;
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<Boolean, Money> isBookingCancelable(){
        if(bookingState.isBookingStateCancelable()){
            HashMap<Boolean, Money> information = new HashMap<>();
            
            throw new UnsupportedOperationException();
        }
        return null;
    }
}
