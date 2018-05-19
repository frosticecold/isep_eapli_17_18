package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.AutoTxPOSRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaAutoTxPOSRepository  extends JpaAutoTxRepository<POS , Long>
        implements AutoTxPOSRepository{
    
    /**
     * Contructor to use with JpaAutoTxRepository
     * @param autoTx Transaction
     * 
     */
    public JpaAutoTxPOSRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    /**
     * Save pos
     * @param entity
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    @Override
    public POS saveTransaction(POS entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return save(entity);
    }

   
    
}
