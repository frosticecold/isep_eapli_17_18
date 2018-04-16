/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.transaction;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;

/**
 *
 * @author MarioDias
 * @param <U> Cafeteria user 
 * @param <K> Meal or Credits
 */
public abstract class Transaction <U extends CafeteriaUser ,K>{
    
    private U user;
    private K k;

    /**
     * 
     * @param user Cafeteria user for the new transaction
     * @param k Either Meal or Credits for cancelation or debit/credit respectively
     * Construction of the object for the new transaction either Debit, Credit or Cancelation
     */
    public Transaction(U user, K k) {
        this.user = user;
        this.k = k;
    }
    
    /**
     *
     * @param user Cafeteria user for the new transaction
     * @param obj Either Meal or Credits for cancelation or debit/credit respectively
     * @return true for success of the operation
     */
    public abstract boolean movement(U user,K obj);
    
}
