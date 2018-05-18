/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.CreditTransaction.TransactionType;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class TransactionBootstrapper implements Action{

    private final TransactionRepository transactionRepository = 
            PersistenceContext.repositories().transactioRepository();
    @Override
    public boolean execute() {
        final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();
        final Optional<CafeteriaUser> user = repo.findByMecanographicNumber(new MecanographicNumber("900330")); 
        
        Transaction transaction_1 = new Transaction(user.get(), TransactionType.CANCELATION, Money.euros(5));
        Transaction transaction_2 = new Transaction(user.get(), TransactionType.DEBIT, Money.euros(4.5));
        Transaction transaction_3 = new Transaction(user.get(), TransactionType.RECHARGE, Money.euros(50));
        
        //register(transaction_1);
        //register(transaction_2);
        //register(transaction_3);
        
        return true;
    }

    private void register(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(TransactionBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
