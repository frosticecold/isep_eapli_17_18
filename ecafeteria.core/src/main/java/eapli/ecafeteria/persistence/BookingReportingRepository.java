/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingStates;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.repositories.ReportingRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ruial
 */
public interface BookingReportingRepository extends ReportingRepository {

    public Iterable<Booking> listServedBookings();

    public Optional<Booking> findNextBooking();

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingStates bookingState);

}
