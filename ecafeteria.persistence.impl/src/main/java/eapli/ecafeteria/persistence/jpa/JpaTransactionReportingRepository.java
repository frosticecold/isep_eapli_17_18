/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.dto.TransactionDTO;
import eapli.ecafeteria.persistence.TransactionReportingRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class JpaTransactionReportingRepository  
        extends JpaAutoTxRepository<Transaction, Long>
        implements TransactionReportingRepository {

    public JpaTransactionReportingRepository(TransactionalContext autoTx) {
        super(autoTx);
    }
    
    /**
     * Show all transactions of an given User
     * 
     * @param user User
     * @return List with transactionDTO
     */
    @Override
    public List<TransactionDTO> showTransactions(CafeteriaUser user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        
        List<Transaction> listTransactions = match("e.cafeteriaUser=:user", params);
        List<TransactionDTO> ret = new ArrayList<>();
        listTransactions.forEach(e -> ret.add(e.toDTO()));
        
        return ret;
    }

}
