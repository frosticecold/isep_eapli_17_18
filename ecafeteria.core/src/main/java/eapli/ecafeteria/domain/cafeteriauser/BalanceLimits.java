/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Entity;
import eapli.framework.domain.money.Money;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Rúben Santos
 */

@Entity
public class BalanceLimits implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "limit")
    private Money balanceLimit;
    
    @OneToOne
    private CafeteriaUser user;
    /**
     * Constructor
     */
    protected BalanceLimits() {
        balanceLimit = Money.euros(0);
    }
    
    protected BalanceLimits(CafeteriaUser user){
        this.user = user;
        this.balanceLimit = Money.euros(0);
    }
    
    /**
     * Define the limits of the user's balance to the amount at which they
     * should be warned for going below the limit
     *
     * @param limits
     * @return
     */
    public boolean defineBalanceLimits(double limits) {
        try {
            if(limits < 0) return false;
            balanceLimit = Money.euros(limits);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Check the balance limit of the user
     *
     * @return the limit
     */
    public double checkBalanceLimits() {
        return balanceLimit.amount();
    }

}
