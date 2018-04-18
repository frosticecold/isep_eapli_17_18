/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class JpaBookingReportingRepository extends CafeteriaJpaRepositoryBase implements BookingReportingRepository {

    /**
     * Finds a list of bookings given a state
     *
     * @param bookingState
     * @return
     */
    @Override
    public Iterable<Booking> findBookingByState(BookingState.BookingStates bookingState) {
        final Query q = entityManager().
                createQuery("SELECT booking "
                        + "FROM Booking booking"
                        + "WHERE booking.BOOKINGSTATE = :bookingState");

        q.setParameter("bookingState", bookingState);
        return q.getResultList();
    }

    public Booking findNextBooking(CafeteriaUser user) {
        Map<String, Object> params = new HashMap<>();
        Booking nextBooking = null;
        BookingState.BookingStates state = BookingState.BookingStates.BOOKED;

        for (Booking booking : findBookingsByCafeteriaUser(user, state)) {

            long bookingDate1 = booking.getMeal().getMealDate().getTimeInMillis();
            long bookingDate2 = nextBooking.getMeal().getMealDate().getTimeInMillis();

            if (bookingDate1 < bookingDate2) {
                nextBooking = booking;
            }
        }

        return nextBooking;
    }

    /**
     * Find booking by cafeteria user that are in a booked state
     *
     * @param user user of the cafeteria
     * @param bookingState
     * @return
     */
    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState.BookingStates bookingState) {
        final Query q = entityManager().
                createQuery("SELECT booking "
                        + "FROM Booking booking"
                        + "AND booking.user = :user"
                        + "AND booking.BOOKINGSTATE = :bookingState");

        q.setParameter("user", user);
        q.setParameter("bookingState", bookingState);
        return q.getResultList();
    }

    @Override
    public List<BookingPerOption> showReportByDay(Date date) {
             
        final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking"
                        + "AND booking.date = :date");
        
        q.setParameter("date", date);

        List<Booking> b = q.getResultList();
        
        List<BookingPerOption> dtoList = new ArrayList<>();
        
        for( Booking booking : b){
            
            
            
            dtoList.add(new BookingPerOption("mealType", date, "mealDishName", "userName"));
        }
        
        
        
        
        
        return dtoList;
        
    }

}
