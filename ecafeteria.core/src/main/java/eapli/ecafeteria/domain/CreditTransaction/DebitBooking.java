/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.pos.ChargeCardController;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class DebitBooking extends Transaction {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionRepository tr = PersistenceContext.repositories().transactioRepository();
    private Transaction t;
    private CafeteriaUser user;

    /**
     * This class is inherited from the generic class Transaction, eith the
     * method movement
     *
     * @param user the user that will lost credits to its current balance
     * @param credits The amount of credits that will deleted from the user
     * account
     */
    public DebitBooking(CafeteriaUser user, Money credits) {
        super(user, credits);
    }

    /**
     * removes the price of the meal of the users balance
     * saves the transaction
     * @param user
     * @param mealPrice
     * @return 
     */
    public boolean movement(CafeteriaUser user, Money mealPrice) {
        t = new DebitBooking(user, mealPrice);
        
        Balance userBalance =  service.getBalanceOfUser(user.mecanographicNumber());
        Money money = userBalance.currentBalance().subtract(mealPrice);
        Balance newBalance = new Balance(money);
        
//        saveTransaction(t);
  
        return tr.setNewBalance(user.mecanographicNumber(), newBalance);
    }

    private void saveTransaction(Transaction t) {
        try {
            tr.save(t);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(ChargeCardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(ChargeCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
