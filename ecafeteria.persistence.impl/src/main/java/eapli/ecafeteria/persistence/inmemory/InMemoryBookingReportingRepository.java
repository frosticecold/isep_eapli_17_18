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
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class InMemoryBookingReportingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingReportingRepository {

    /**
     * Finds a list of bookings given a state
     * @param bookingState
     * @return 
     */
    @Override
    public Iterable<Booking> findBookingByState(BookingState.BookingStates bookingState) {
       return match(e -> e.getBookingState().actualState().equals(bookingState));
    }
    
    @Override
    public Booking findNextBooking(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState.BookingStates bookingState) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterable<BookingPerOption> showReportByDay(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
