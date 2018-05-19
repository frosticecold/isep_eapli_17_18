/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.CreditTransaction.TransactionType;
import eapli.ecafeteria.domain.CreditTransaction.ChargeCreditsEvent;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Currency;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 *
 * @author MarioDias
 */
public class ChargeCardController implements Controller, Observer {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionRepository tr = PersistenceContext.repositories().movementTransactions();
    private Transaction t;
    private String mecNumber;
    private CafeteriaUser user;
    private boolean errorFlag = false;
    //creation of the observer that trigger a given event    
    //in this case is the to check the consistency of the any charging 
    //movements to a given Cafeteria User
    private ChargeCreditsEvent observer = new ChargeCreditsEvent();

    public String findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        this.mecNumber = mecanographicNumber;
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        this.user = user.get();
        return this.user.cafeteriaUserNameAndCurrentBalance();
    }

    public boolean createMovementCharging(double tempCredits) {
        Money credits = new Money(tempCredits, Currency.getInstance("EUR"));

        this.t = new Transaction(this.user, TransactionType.RECHARGE, credits);

        return t != null;
    }

    public void saveMovementChargingTransaction() throws DataConcurrencyException, DataIntegrityViolationException {
        observer.addObserver(this);

        t = this.tr.saveTransaction(this.t);
        if (this.t == null) {
            throw new IllegalArgumentException("Error Saving transaction");
        }
        t.addObserver(observer);
        //because there is need to run the update method in the observed class
        //and the changed state only occurs in the constructor we will need to update his state to run the update in the observers
        t.alterState();

        t.notifyObservers(user);
    }

    /**
     * This method will rollback the transaction if there was an error.
     * <p>
     * @return true if there was an error to notify the current system user
     * @throws DataIntegrityViolationException errors deleting the transaction
     */
    public void checkMovementPersistence() throws DataIntegrityViolationException, Exception {
        if (errorFlag) {
            tr.delete(t);
            throw new Exception("Error occurred in the charging movement, please repeat or contact support.");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        CafeteriaUser updatedUser = (CafeteriaUser) arg;
        Money updatedBalance = updatedUser.currentMoney();
        //find the user uin the database
        this.user = service.findCafeteriaUserByMecNumber(mecNumber).get();
        //get the ammount of money after the persistence in database
        Money money = user.currentMoney();
        // check for consistency in the user balance
        if (!money.equals(updatedBalance)) {
            //if there is a diference something went wrong, we need to flag it to rollback the transaction
            errorFlag = true;
        }
    }
}
