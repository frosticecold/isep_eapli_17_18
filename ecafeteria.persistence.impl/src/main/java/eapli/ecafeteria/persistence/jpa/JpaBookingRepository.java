/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long> implements BookingRepository {

    
    
    public Booking saveBooking(Booking entity) throws DataConcurrencyException, 
            DataIntegrityViolationException{
        
        return save(entity);
    }
}
