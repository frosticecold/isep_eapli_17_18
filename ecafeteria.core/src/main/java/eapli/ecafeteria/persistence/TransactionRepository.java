package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
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
    Iterable<Transaction> findAllTransactionsByCafeteriaUserAndType(CafeteriaUser user, String transactionType);

    public Iterable<Transaction> findAllActive();

}
