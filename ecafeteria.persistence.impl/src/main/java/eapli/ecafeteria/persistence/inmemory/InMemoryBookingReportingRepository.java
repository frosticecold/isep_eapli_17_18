/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingStates;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ruial
 */
public class InMemoryBookingReportingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingReportingRepository {

    @Override
    public Iterable<Booking> listServedBookings() {
       return match(e -> e.isAvailableForRating());
    }
    
    
    @Override
    public Booking findNextBooking(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingStates bookingState) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
