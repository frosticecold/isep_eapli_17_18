package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.POS;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public interface AutoTxPOSRepository {
    
    public POS saveTransaction(POS entity) throws DataConcurrencyException, DataIntegrityViolationException;
}
