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
 * @author da_ro
 */
public class CancelationBooking extends Transaction<CafeteriaUser, Money>{

    public CancelationBooking(CafeteriaUser user, Money k) {
        super(user, k);
    }

    @Override
    public boolean movement(CafeteriaUser user, Money obj) {
        return false;
    }
    
}
