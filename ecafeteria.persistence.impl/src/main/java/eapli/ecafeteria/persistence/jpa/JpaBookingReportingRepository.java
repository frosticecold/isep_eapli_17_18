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
     * @author Rui Almeida <1160818>
     * @return
     */
    @Override
    public Iterable<Booking> findBookingByState(BookingState bookingState) {
        return entityManager().createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.bookingState = :bookingState")
                .setParameter("bookingState", bookingState)
                .getResultList();
    }

    @Override
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
                        + "FROM Booking booking "
                        + "WHERE booking.user = :user "
                        + "AND booking.BOOKINGSTATE = :bookingState ");

        q.setParameter("user", user);
        q.setParameter("bookingState", bookingState);
        return q.getResultList();
    }

    @Override
    public Iterable<BookingPerOption> showReportByDay(Date date) {
             
//        final Query q = entityManager().
//        createQuery("SELECT booking "
//                        + "FROM Booking booking"
//                        + "AND booking.date = :date");
//        
//        q.setParameter("date", date);
//
//        return q.getResultList();

          /* ^QUERY */
          
          
          List<BookingPerOption> l = new ArrayList<>();
          l.add(new BookingPerOption("mealType", new Date(), "mealDishName", "userName"));
          l.add(new BookingPerOption("mealType2", new Date(), "mealDishName4", "userName12"));
          l.add(new BookingPerOption("mealType3", new Date(), "mealDishName33", "userName4"));
          
          
          
          return l;
    }

}
