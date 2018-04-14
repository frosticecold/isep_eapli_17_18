package eapli.ecafeteria.domain.cafeteriauser;

/**
 *
 * @author MarioDias
 */
public class Balance {

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
    public void addCredits(Credits credits){
        this.currentBalance+=credits.getCreditsAmount();
    }

}
