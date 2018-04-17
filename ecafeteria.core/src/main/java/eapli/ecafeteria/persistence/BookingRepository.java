/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.List;

/**
 *
 * @author João Rocha 1161838
 */
public interface BookingRepository {

    public Booking findNextBooking(CafeteriaUser user);

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user);

    public List<Booking> findConsumedBookingWithoutRating();

}
