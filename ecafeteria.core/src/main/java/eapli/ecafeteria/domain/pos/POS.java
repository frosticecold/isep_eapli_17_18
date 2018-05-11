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
public class POS implements AggregateRoot<Long>, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPOS")
    private long idPOS;  
    
    private boolean open;
    
    @Transient
    private SystemUser cashier;
    
    @OneToOne
    private DeliveryMealSession session;

    protected POS () {
        //for ORM only
    }
    
    public POS (SystemUser posUser) {
        if(posUser != null) this.cashier = posUser;
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
        if(this.open)this.open = false;
        else this.open = true;
    }
        
    /**
     * Return the cashier
     * @return 
     */    
    public SystemUser cashier() {
        return this.cashier;
    }
    
    private void openSession() {
        
        //this.session = new DeliveryMealSession
    }
 }
