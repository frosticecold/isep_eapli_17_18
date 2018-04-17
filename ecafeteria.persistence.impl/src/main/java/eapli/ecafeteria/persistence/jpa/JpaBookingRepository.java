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
import java.util.Optional;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long>implements BookingRepository {

    public Optional<Booking> findNextBooking(CafeteriaUser user) {
        Map<String,Object> params = new HashMap<>();
        Booking nextBooking = null;
        BookingStates state = BookingStates.BOOKED;
        
        for(Booking booking : findBookingsByCafeteriaUser(user)){
            //if(booking.getMeal().)
        }
        
        
        
        
        
        return null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
