/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.dto.TransactionDTO;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.TransactionReportingRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.List;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class ShowTransactionsController {

    /**
     * Factory
     */
    private RepositoryFactory factory = null;

    /**
     * Cafeteria user
     */
    private CafeteriaUser user = null;
    
    /**
     * Transaction reporting repository
     */
    private TransactionReportingRepository reportingRepository;
    
    /**
     * List with transactionDTO
     */
    private List<TransactionDTO> list = null;
    
    public ShowTransactionsController() {
        this.factory = PersistenceContext.repositories();
        
        this.user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();
        final TransactionalContext TxCtx 
                = PersistenceContext.repositories().buildTransactionalContext();
        
        this.reportingRepository = PersistenceContext.repositories().transactionReportingRepository(TxCtx);
        
        TxCtx.beginTransaction();
        this.list = reportingRepository.showTransactions(user);
        TxCtx.commit();
    }
    
    /**
     * Shows transactions
     * 
     * @return list with all transactions of the user
     */
    public List<TransactionDTO> showTransactions(){
        return list;
    }
}
