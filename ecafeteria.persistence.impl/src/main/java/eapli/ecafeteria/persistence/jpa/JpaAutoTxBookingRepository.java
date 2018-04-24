/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.AutoTxBookingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class JpaAutoTxBookingRepository
        extends JpaAutoTxRepository<Booking, Long>
        implements AutoTxBookingRepository{

    /**
     * Contructor to use with JpaAutoTxRepository
     * @param autoTx Transaction
     * 
     * @author David Camelo <1161294@isep.ipp.pt>
     */
    public JpaAutoTxBookingRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    /**
     * Persist Booking to DB
     * 
     * @param entity booking to be safed
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     * 
     * @author David Camelo <1161294@isep.ipp.pt>
     */
    @Override
    public Booking saveBooking(Booking entity) throws DataConcurrencyException,
            DataIntegrityViolationException {

        return save(entity);
    }
    
}
