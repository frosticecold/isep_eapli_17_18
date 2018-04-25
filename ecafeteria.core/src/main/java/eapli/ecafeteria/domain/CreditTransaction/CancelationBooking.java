/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import javax.persistence.Entity;

/**
 *
 * @author David Camelo 1161294@isep.ipp.pt
 */
@Entity
public class CancelationBooking extends Transaction {

    private static final long serialVersionUID = 1L;
    
    public CancelationBooking(CafeteriaUser user, Money k) {
        super(user, k);
        this.transactionType = "Cancel";
    }

    protected CancelationBooking() {
        this.transactionType = "Cancel";
        // for ORM only
    }
    
    @Override
    public boolean movement(CafeteriaUser user, Money credits) {
        return user.addCredits(credits);
    }

    @Override
    public String toString() {
        return "Transaction type: " + transactionType + " Amount: " 
                + super.k + " " + super.cafeteriaUser.id();
    }

}
