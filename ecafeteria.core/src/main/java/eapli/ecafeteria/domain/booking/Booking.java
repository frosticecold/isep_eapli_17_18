/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import java.io.Serializable;
import javax.persistence.Entity;
import eapli.ecafeteria.domain.cafeteriauser.*;
import eapli.ecafeteria.domain.meal.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Column(name = "BOOKINGSTATE")
    private BookingState bookingState;

    @OneToOne
    private CafeteriaUser cafeteriaUser;

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

    public boolean isAvailableForRating() {
        if (bookingState.actualState().equals(BookingStates.SERVED)) {
            return true;
        } else if (bookingState.actualState().equals(BookingStates.NOT_SERVED)) {
            return true;
        }
        return false;
    }

}
