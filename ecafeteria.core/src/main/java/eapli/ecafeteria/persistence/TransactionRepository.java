package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

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
    
    /**
     * Searches for transactions between a period.
     * @param user user that the transactions belong to
     * @param start start date to search in yyyy-mm-dd format
     * @param end end date to search in yyyy-mm-dd format
     * @author Rui Almeida <1160818>
     * @return iterable with the user's transactions
     */
    List<Transaction> findUserTransactionsBetweenPeriod(CafeteriaUser user, String start, String end);
    
    
    public Iterable<Transaction> findAllActive();

}
