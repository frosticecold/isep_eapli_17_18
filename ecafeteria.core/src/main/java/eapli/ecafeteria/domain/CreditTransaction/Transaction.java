/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author MarioDias
 * @param <U> Cafeteria user
 * @param <K> Credits, theh mpney for the transaction
 */
@Entity
public abstract class Transaction implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @ManyToOne
    protected CafeteriaUser cafeteriaUser;
    protected Money k;
    protected String transactionType;
    @OneToOne
    protected SystemUser systemUser;
    @Temporal(TemporalType.DATE)
    protected Calendar date;

    @Version
    protected Long version;

    /**
     *
     * @param user Cafeteria user for the new transaction
     * @param k Either Meal or Credits for cancelation or debit/credit
     * respectively Construction of the object for the new transaction either
     * Debit, Credit or Cancelation
     */
    public Transaction(CafeteriaUser user, Money k) {
        this.cafeteriaUser = user;
        this.k = k;
        this.date = DateTime.now();
        this.systemUser = AuthorizationService.session().authenticatedUser();
    }

    protected Transaction() {
        // for ORM only
    }

    /**
     *
     * @param user Cafeteria user for the new transaction
     * @param obj Either Meal or Credits for cancelation or debit/credit
     * respectively
     * @return true for success of the operation
     */
    public abstract boolean movement(CafeteriaUser user, Money obj);

    @Override
    public abstract String toString();

    
}
