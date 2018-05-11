/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.dto;

import eapli.ecafeteria.domain.CreditTransaction.TransactionType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import eapli.framework.dto.DTO;
import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class TransactionDTO implements DTO{

    public CafeteriaUser cafeteriaUser;
    public Money money;
    public TransactionType transactionType;
    public SystemUser systemUser;
    public Calendar date;
    
    public TransactionDTO(CafeteriaUser user, TransactionType transactionType, 
            Money money, Calendar date, SystemUser systemUser) {
        this.cafeteriaUser = user;
        this.transactionType = transactionType;
        this.money = money;
        this.date = date;
        this.systemUser = systemUser;
    }
    
    @Override
    public String toString() {
        String strDate = DateTime.convertCalendarToDayMonthYearAndDayName(date);
        return strDate + " -> " + transactionType + ": " + money.toString();
    }  
}
