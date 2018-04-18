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
import java.util.List;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author ruial
 */
public class JpaBookingReportingRepository extends CafeteriaJpaRepositoryBase implements BookingReportingRepository {

    @Override
    public Iterable<Booking> listServedBookings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Booking> findNextBooking() {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
