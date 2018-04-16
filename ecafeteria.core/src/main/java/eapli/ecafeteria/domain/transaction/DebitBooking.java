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
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class DebitBooking extends Transaction<CafeteriaUser, Money> {
    
    /**
     * This class is inherited from the generic class Transaction, eith the method
     * movement
     * @param user the user that will lost credits to its current balance
     * @param credits The amount of credits that will deleted from the user account 
     */
    public  DebitBooking(CafeteriaUser user, Money credits) {
        super(user, credits);
    }

    @Override
    public boolean movement(CafeteriaUser user, Money credits) {
        return user.removeCredits(credits);
    }
}
