/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author ruial
 */
public class InMemoryBookingReportingRepository extends InMemoryRepositoryWithLongPK<Booking> implements BookingReportingRepository {

    @Override
    public Iterable<Booking> listServedBookings() {
       return match(e -> e.isAvailableForRating());
    }

}
