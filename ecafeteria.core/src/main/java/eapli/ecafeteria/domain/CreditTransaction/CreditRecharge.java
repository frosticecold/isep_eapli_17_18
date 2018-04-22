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
 * @author Mario Dias
 */
@Entity
public class CreditRecharge extends Transaction implements Serializable {

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
    public CreditRecharge(CafeteriaUser user, Money credits) {
        super(user, credits);
        this.transactionType = "Credit";
    }

    protected CreditRecharge() {
        // for ORM only
    }

    @Override
    public boolean movement(CafeteriaUser user, Money credits) {
        return user.addCredits(credits);
    }

}
