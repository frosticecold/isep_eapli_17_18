/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author MarioDias
 */
public interface TransactionRepository extends DataRepository<Transaction, Long> {

    /**
     * returns the Transaction , debit or credit
     *
     * @param number
     * @return
     */
    Iterable<Transaction> findAllTransactionsByMecanographicNumberAndType(MecanographicNumber number,String transactionType);

    public Iterable<Transaction> findAllActive();

}
