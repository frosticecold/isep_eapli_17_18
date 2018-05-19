package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
//Entity that represents a session start in a point of sale
@Entity
public class POS implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPOS")
    private long id;

    private boolean open;
    private long identification;
    @Transient
    private SystemUser cashier;

    @OneToOne
    private DeliveryMealSession session;

    protected POS() {
        //for ORM only
    }

    public POS(SystemUser posUser) {
        if (posUser != null) {
            this.cashier = posUser;
        }
        this.open = false;
        this.identification = Long.valueOf("1");
    }

    /**
     * Compares this object with another
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {

        boolean finalResult = false;

        if (other.getClass() == this.getClass()) { //check if "other" is a POS entity aswell
            //checks if it has same ID (only atribute of entity that will be unique)
            POS p = (POS) other;
            if (p.id() == this.id()) {
                finalResult = true;
            }
        }

        return finalResult;
    }

    /**
     * Return the id of POS
     *
     * @return
     */
    @Override
    public Long id() {
        return this.id;
    }

    /**
     * Check if pos is closed
     *
     * @return
     */
    public boolean checkState() {
        return this.open;
    }

    /**
     * open pos
     */
    public void openPOS() {

        this.open = true;
    }

    /**
     * close pos
     */
    public void closePOS() {

        this.open = false;
    }

    /**
     * Return the cashier
     *
     * @return
     */
    public SystemUser cashier() {
        return this.cashier;
    }

    /**
     * Creates a new session and returns it
     *
     * @return
     */
    public DeliveryMealSession openSession() {

        this.session = new DeliveryMealSession(this);
        
        return this.session;
    }
}
