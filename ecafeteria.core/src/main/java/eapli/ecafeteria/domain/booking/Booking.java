/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.Application;
import java.io.Serializable;
import javax.persistence.Entity;
import eapli.ecafeteria.domain.cafeteriauser.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.framework.domain.money.Money;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

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
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar calendar;

    public Booking(Meal meal, BookingState bookingState, CafeteriaUser cafeteriaUser, Calendar date) {
        this.meal = meal;
        this.bookingState = bookingState;
        this.cafeteriaUser = cafeteriaUser;
        this.calendar = date;
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
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        if (!Objects.equals(this.bookingState, other.bookingState)) {
            return false;
        }
        if (!Objects.equals(this.cafeteriaUser, other.cafeteriaUser)) {
            return false;
        }
        if (!Objects.equals(this.calendar, other.calendar)) {
            return false;
        }
        return true;
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
     * Informs if it is possible to cancel the booking
     * 
     * @return true if possible
     * @author David Camelo <1161294@isep.ipp.pt>
     */
    public boolean isBookingCancelable(){
        return bookingState.isBookingStateCancelable();
    }
    
    /**
     * Calculates the refund for an possible cancelation
     * Return null if no cancelation is possible
     * 
     * @return Money - if refund possible | null if refund is not possible
     * @author David Camelo <1161294@isep.ipp.pt>
     */
    public Money refundForCancelation(){
        if (isBookingCancelable()) {
            final int limit_hour_day_before_no_cost = Application.settings().getLIMIT_HOUR_DAY_BEFORE_NO_COST();
            final int dinner_limit_hour_day_before_no_cost = Application.settings().getDINNER_LIMIT_HOUR_DAY_BEFORE_NO_COST();
        
            Calendar actual = Calendar.getInstance();
            
            if(actual.compareTo(meal.getMealDate()) < 0){
                int hours_diff = (int) Math.abs((actual.getTimeInMillis() - meal.getMealDate().getTimeInMillis()) 
                        / (60 * 60.0 * 1000.0));
                if(MealType.DINNER == meal.mealtype()){
                    if(hours_diff >= dinner_limit_hour_day_before_no_cost){
                        return meal.dish().currentPrice();
                    }else{
                        Money price = meal.dish().currentPrice();
                        return new Money(price.amount()/2.0, price.currency());
                    }
                    
                }else{
                    // Lunch
                    if(hours_diff >= limit_hour_day_before_no_cost){
                        return meal.dish().currentPrice();
                    }else{
                        Money price = meal.dish().currentPrice();
                        return new Money(price.amount()/2.0, price.currency());
                    }
                }
            }else{
                return null;
            }
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

    @Override
    public String toString() {
        return "Booking: id " + this.idBooking + "| " + meal;
    }

    
}
