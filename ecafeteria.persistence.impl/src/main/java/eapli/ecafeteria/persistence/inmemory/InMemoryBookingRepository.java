/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import java.util.List;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class InMemoryBookingRepository implements BookingRepository {

    @Override
    public Booking findNextBooking(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Booking> findConsumedBookingWithoutRating() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
