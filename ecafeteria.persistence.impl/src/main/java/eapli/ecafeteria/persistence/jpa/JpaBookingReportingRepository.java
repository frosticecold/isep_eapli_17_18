/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
        BookingState state = new BookingState();

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
     * Find booking by cafeteria user that are in a specific state
     *
     * @param user user of the cafeteria
     * @param bookingState booking state
     * @author David Camelo <1161294@isep.ipp.pt>
     * 
     * @return list with bookings
     */
    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, 
            BookingState bookingState) {
        Query query = entityManager().createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.bookingState = :bookingState "
                        + "AND booking.cafeteriaUser = :cafeteriaUser");
        
        query.setParameter("bookingState", bookingState);
        query.setParameter("cafeteriaUser", user);
        
        return query.getResultList();
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
