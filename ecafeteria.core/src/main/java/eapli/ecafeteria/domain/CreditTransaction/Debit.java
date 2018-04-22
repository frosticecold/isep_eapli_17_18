/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Debit extends Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionType;

    /**
     * This class is inherited from the generic class Transaction, eith the
     * method movement
     *
     * @param user the user that will receive new credits to its current balance
     * @param credits The amount of credits that will be added to the user
     * amount
     */
    public Debit(CafeteriaUser user, Money credits) {
        super(user, credits);
        this.transactionType = "Debit";
    }

    protected Debit() {
        // for ORM only
    }

    @Override
    public boolean movement(CafeteriaUser user, Money obj) {
        return user.removeCredits(obj);
    }

    @Override
    public String toString() {
        return "Debit{" + "transactionType=" + transactionType + '}';
    }

}
