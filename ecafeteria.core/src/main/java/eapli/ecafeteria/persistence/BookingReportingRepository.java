package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.persistence.repositories.ReportingRepository;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ruial
 */
public interface BookingReportingRepository extends ReportingRepository {

    public Iterable<Booking> findBookingByState(BookingState bookingState);

    public Booking findNextBooking(CafeteriaUser user);

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState.BookingStates bookingState);
    
     public Iterable<BookingPerOption> showReportByDay(Date date);
   
    
}
