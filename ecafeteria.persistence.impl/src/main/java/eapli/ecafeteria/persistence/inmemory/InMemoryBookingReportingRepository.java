/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingReportingRepository;

/**
 *
 * @author ruial
 */
public class InMemoryBookingReportingRepository implements BookingReportingRepository {

    @Override
    public Iterable<Booking> listServedBookings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
