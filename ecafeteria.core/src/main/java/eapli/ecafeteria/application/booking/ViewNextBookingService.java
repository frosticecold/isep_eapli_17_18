/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewNextBookingService {

    private static final BookingReportingRepository bookingReportingRepo = PersistenceContext
            .repositories().bookingReporting();

    public ViewNextBookingService(){
        
    }
    /**
     * Finds the users next booking in the booked state
     *
     * @param user
     * @author Joao Rocha 1161838
     * @return
     */
    public Booking findNextBooking(CafeteriaUser user) {
        Booking nextBooking = null;
        BookingState state = new BookingState();

        for (Booking booking : bookingReportingRepo.findBookingsByCafeteriaUser(user, state)) {

            long bookingDate1 = booking.getMeal().getMealDate().getTimeInMillis();
            long bookingDate2;

            if (nextBooking == null) {
                bookingDate2 = Long.MAX_VALUE;
            } else {
                bookingDate2 = nextBooking.getMeal().getMealDate().getTimeInMillis();
            }

            if (bookingDate1 < bookingDate2) {
                nextBooking = booking;
            }
        }

        return nextBooking;
    }
}
