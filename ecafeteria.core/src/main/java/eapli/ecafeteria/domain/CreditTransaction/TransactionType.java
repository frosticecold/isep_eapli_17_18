/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.domain.CreditTransaction;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
@Entity
public enum TransactionType {
    @Column(name = "Cancelation") CANCELATION{
        @Override
        public String toString(){
            return "CANCELATION";
        }
    },
    @Column(name = "Debit") DEBIT{
        @Override
        public String toString(){
            return "DEBIT";
        }
    },
    @Column(name = "Recharge") RECHARGE{
        @Override
        public String toString(){
            return "RECHARGE";
        }
    }
}
