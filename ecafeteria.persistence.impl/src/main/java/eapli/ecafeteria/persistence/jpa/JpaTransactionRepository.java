package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MarioDias
 */
public class JpaTransactionRepository extends JpaAutoTxRepository<Transaction, Long> implements TransactionRepository {

    public JpaTransactionRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    public JpaTransactionRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public Iterable<Transaction> findAllActive() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterable<Transaction> findAllTransactionsByCafeteriaUserAndType(CafeteriaUser user, String transactionType) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("ttype", transactionType);
        return match("e.cafeteriaUser=:user AND e.transactionType=:ttype", params);
    }
    
    @Override
    public Transaction saveTransaction(Transaction entity) 
            throws DataConcurrencyException, 
            DataIntegrityViolationException {
        return save(entity);
    }


    /**
     * Searches for transactions between a period.
     * @param user user that the transactions belong to
     * @param start start date to search in yyyy-mm-dd format
     * @param end end date to search in yyyy-mm-dd format
     * @author Rui Almeida <1160818>
     * @return iterable with the user's transactions
     */
    @Override
    public List<Transaction> findUserTransactionsBetweenPeriod(CafeteriaUser user, String start, String end) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        return match("e.cafeteriaUser=:user and e.date >= '" + start + "' AND e.date <= '" + end + "'", params);
    }

}
