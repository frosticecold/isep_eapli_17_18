/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.List;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class InMemoryBookingReportingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingReportingRepository {

    /**
     * Finds a list of bookings given a state
     * @param bookingState
     * @author Rui Almeida <1160818>
     * @return 
     */
    @Override
    public Iterable<Booking> findBookingByState(BookingState bookingState) {
       return match(e -> e.getBookingState().actualState().equals(bookingState.actualState()));
    }
    
    @Override
    public Booking findNextBooking(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState.BookingStates bookingState) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
