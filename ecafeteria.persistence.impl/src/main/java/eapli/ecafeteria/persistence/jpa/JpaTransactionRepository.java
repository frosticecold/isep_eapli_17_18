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

}
