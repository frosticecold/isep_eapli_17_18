/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author MarioDias
 */
public class ChargeCreditsEvent extends Observable implements Observer {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private CafeteriaUser user;
    private Transaction transaction;

    @Override
    public void update(Observable o, Object arg) {
        user = (CafeteriaUser) arg;
        transaction = (Transaction) o;
        updateCafeteriaUserBalance();
        setChanged();
        //after the update to the cafeteria user, now its time to notify who is interested in this event
        this.notifyObservers(user);
    }

    /**
     * This method will be in charge of persist the user that will be added the
     * credits/money to the account
     */
    public void updateCafeteriaUserBalance() {
        user.addCredits(transaction.retrieveQuantityOfMoneyOfTheMovement());
        service.save(user);
    }

}
