package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.money.Money;
import java.util.Currency;
import javax.persistence.Embeddable;

/**
 *
 * @author MarioDias
 */
@Embeddable
public class Balance {

    private static final long serialVersionUID = 1L;

    private Money currentBalance;

    /**
     *
     */
    protected Balance() {
        this.currentBalance = new Money(0, Currency.getInstance("EUR"));
    }

    /**
     *
     * @param credits is the new value to add to the currentBalance
     * @author Mario Dias
     */
    public boolean addCredits(Money credits) {
        Money moneyAfterTransaction = this.currentBalance.add(credits);
        if (moneyAfterTransaction.equals(currentBalance)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return currentBalance.toString();
    }

}
