/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingStates;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long> implements BookingRepository {

    /**
     * EXPERIMENTAL Method that finds the User's next booking at the booked
     * state
     *
     * @param user
     * @return
     */
    public Booking findNextBooking(CafeteriaUser user) {
        Map<String, Object> params = new HashMap<>();
        Booking nextBooking = null;
        BookingStates state = BookingStates.BOOKED;

        for (Booking booking : findBookingsByCafeteriaUser(user)) {

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
     * @return
     */
    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user) {
        final Map<String, Object> params = new HashMap<>();

        params.put("cafeteriaUser", user);
        params.put("bookingState", BookingStates.BOOKED);

        return match("e.cafeteriaUser =:cafeteriaUser "
                + "AND e.bookingState =: bookingState", params);
    }

    @Override
    public List<Booking> findConsumedBookingWithoutRating() {
        final Query query = entityManager().
                createQuery("SELECT booking"
                + "FROM Booking booking "
                + "WHERE booking.bookingState = :bookingState "
                + "AND 0 = (SELECT COUNT(rating) "
                    + "FROM Rating rating "
                    + "WHERE rating.id = booking.idBooking)", Booking.class);
        
        query.setParameter("bookingState", BookingStates.SERVED);
        return query.getResultList();
    }
}
