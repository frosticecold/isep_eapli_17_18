package eapli.ecafeteria.domain.cafeteriauser;

import javax.persistence.Embeddable;

/**
 *
 * @author MarioDias
 */
@Embeddable
public class Balance {

    private static final long serialVersionUID = 1L;

    private float currentBalance;

    /**
     *
     */
    protected Balance() {
        this.currentBalance = 0;
    }
    
    /**
     *
     * @param credits is the new value to add to the currentBalance
     * @author Mario Dias
     */
    public void addCredits(Credits credits) {
        this.currentBalance += credits.getCreditsAmount();
    }

    @Override
    public String toString() {
        return String.format("%.2f", currentBalance);
    }

}
