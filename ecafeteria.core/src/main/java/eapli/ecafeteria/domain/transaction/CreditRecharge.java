/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.transaction;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;

/**
 * 
 * @author Mario Dias
 */
public class CreditRecharge extends Transaction<CafeteriaUser, Money> {

    /**
     * This class is inherited from the generic class Transaction, eith the method
     * movement
     * @param user the user that will receive new credits to its current balance
     * @param credits The amount of credits that will be added to the user amount
     */
    public CreditRecharge(CafeteriaUser user, Money credits) {
        super(user, credits);
    }

    @Override
    public boolean movement(CafeteriaUser user, Money credits) {
        return user.addCredits(credits);
    }

}
