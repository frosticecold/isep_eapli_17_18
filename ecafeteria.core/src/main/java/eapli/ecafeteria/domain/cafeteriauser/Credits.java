package eapli.ecafeteria.domain.cafeteriauser;

/**
 *
 * @author MarioDias
 */
public class Credits {

    private float credits;

    /**
     *
     * @param credits Is the ammount of money put into a movement
     * @author Mario Dias
     */
    public Credits(float credits) {
        if (credits < 0) {
            throw new IllegalArgumentException();
        }
        this.credits = credits;
    }

    /**
     *
     * @return the value to update the current balance of the user
     */
    public float getCreditsAmount() {
        return credits;
    }
}
