/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import java.io.Serializable;
import javax.persistence.Entity;
import eapli.ecafeteria.domain.cafeteriauser.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.framework.domain.money.Money;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IDBOOKING")
    private Long idBooking;

    @OneToOne
    private Meal meal;
    
    @Embedded
    private BookingState bookingState;

    @OneToOne(cascade = CascadeType.ALL)
    private CafeteriaUser cafeteriaUser;
    
    private Date date;

    public Booking(Meal meal, BookingState bookingState, CafeteriaUser cafeteriaUser, Date date) {
        this.meal = meal;
        this.bookingState = bookingState;
        this.cafeteriaUser = cafeteriaUser;
        this.date = date;
    }
    

    public Booking(Meal meal, CafeteriaUser cafeteriauser) {
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

    public Long getIdBooking() {
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
     * @return true if possible
     */
    public boolean isBookingCancelable(){
        return bookingState.isBookingStateCancelable();
    }
    
    /**
     * Calculates the refund for an possible cancelation
     * Return null if no cancelation is possible
     * 
     * @return Money - if refund possible | null if refund is not possible
     */
    public Money refundForCancelation(){
        if (isBookingCancelable()) {
            HashMap<Boolean, Money> information = new HashMap<>();
            throw new UnsupportedOperationException();
        }else return null;
    }

    public boolean isAvailableForRating() {
        if (bookingState.actualState().equals(BookingState.BookingStates.SERVED)) {
            return true;
        } else if (bookingState.actualState().equals(BookingState.BookingStates.NOT_SERVED)) {
            return true;
        }
        return false;
    }
}
