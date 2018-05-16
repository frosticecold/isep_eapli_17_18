/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.dto.TransactionDTO;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public interface TransactionReportingRepository 
        extends DataRepository<Transaction, Long> {

    
     public List<TransactionDTO> showTransactions(CafeteriaUser user);
}
