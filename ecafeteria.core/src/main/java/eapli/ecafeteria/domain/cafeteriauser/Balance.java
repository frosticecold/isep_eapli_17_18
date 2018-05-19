package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.domain.money.Money;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author MarioDias
 */
@Embeddable
public class Balance implements ValueObject, Serializable{

    private static final long serialVersionUID = 1L;

    private Money currentBalance;
      
    
    /**
     *
     */
    protected Balance() {
//        this.currentBalance = new Money(0, Currency.getInstance("EUR"));
        this.currentBalance = Money.euros(0);
    }

     public Balance(Money currentBalance) {
        this.currentBalance = currentBalance;
    }

    
    /**
     *
     * @param credits is the new value to add to the currentBalance
     * @author Mario Dias
     */
    public boolean addCredits(Money credits) {
        this.currentBalance = this.currentBalance.add(credits);
        return true;
    }
    
     /**
     * check if there is enough money to make a transaction 
     * @param credits is the new value to compare to the user balance
     * @author Beatriz Ferreira
     */
    public boolean hasEnoughCredits(Money credits) {
       return this.currentBalance.greaterThanOrEqual(credits);
  
    }
    
     /**
     *
     * @param credits is the new value to remove to the currentBalance
     * @author Beatriz Ferreira
     */
    public boolean removeCredits(Money credits) {
        this.currentBalance = this.currentBalance.subtract(credits);
        return true;
    }

    public Money currentBalance() {
        return currentBalance;
    }

    /**
     * Checks if user balance is within the limits defined by him
     * @param limits
     * @return 
     */
    public boolean balanceIsWithinUserLimits(double limits){
        return currentBalance.amount() > limits;
    }
    
    @Override
    public String toString() {
        return currentBalance.toString();
    }
}
