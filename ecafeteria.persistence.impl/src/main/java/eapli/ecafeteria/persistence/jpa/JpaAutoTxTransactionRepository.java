/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.persistence.AutoTxTransactionRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class JpaAutoTxTransactionRepository 
        extends JpaAutoTxRepository<Transaction, Long> 
        implements AutoTxTransactionRepository{
    
    public JpaAutoTxTransactionRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    @Override
    public Transaction saveTransaction(Transaction entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return save(entity);
    }
}
