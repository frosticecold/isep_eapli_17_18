/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public interface AutoTxBookingRepository {
    
    public Booking saveBooking(Booking entity) throws DataConcurrencyException, 
            DataIntegrityViolationException;
}
