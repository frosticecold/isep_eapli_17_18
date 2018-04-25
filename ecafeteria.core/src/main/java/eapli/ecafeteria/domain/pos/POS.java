package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

//Entity that represents a session start in a point of sale

@Entity
public class POS implements AggregateRoot<Long>, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPOS")
    private long idPOS; 
        
    @Transient
    private long identification;
    
    @Transient
    private boolean open;
    
    @Transient
    private SystemUser cashier;

    protected POS () {
        //for ORM only
    }
    
    public POS (SystemUser posUser) {
        this.cashier = posUser;
        this.createNewSession(this.cashier());
        this.identification = 1;
        this.open = false;
    }

    /**
     * Compares this object with another
     * @param other
     * @return 
     */
    @Override
    public boolean sameAs(Object other) {
        
        boolean finalResult = false;
        
        if(other.getClass() == this.getClass()) { //check if "other" is a POS entity aswell
           //checks if it has same ID (only atribute of entity that will be unique)
           POS p = (POS)other;
           if(p.id() == this.id()) {
               finalResult = true;
           }
        }
        
        return finalResult;
    }

    /**
     * Return the id of POS
     * @return 
     */
    @Override
    public Long id() {
       return this.idPOS;
    }

    /**
     * Check if pos is closed
     * @return 
     */
    public boolean isClosed() {
        return this.open;
    }

    /**
     * change state of POS
     */
    public void changeState() {
        this.open = !this.open;
    }
    
    /**
     * Creates a new session when entity is created , session represents a users login on this POS
     */
    private void createNewSession(SystemUser user) {
        
        DeliveryMealSession session = new DeliveryMealSession(this);
    }
    
    /**
     * Return the cashier
     */    
    public SystemUser cashier() {
        
        return this.cashier;
    }
 }