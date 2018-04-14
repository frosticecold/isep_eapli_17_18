/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.Transaction;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;


/**
 *
 * @author MarioDias
 */
public class CreditRecharge extends Transaction<CafeteriaUser, Money>{

    public CreditRecharge(CafeteriaUser user, Money credits) {
        super(user, credits);
    }

    @Override
    public boolean movement(CafeteriaUser user, Money credits) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
