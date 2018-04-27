package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

@Entity
public class DeliveryMealSession implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="IDDELIVERYMEALSESSION")
    private Long idSession;
        
    @Temporal(TemporalType.DATE)
    private DeliverySessionDate sessionDate;
    
    @OneToOne
    @JoinColumn(name="POS")
    private POS pos;
    
    @OneToOne
    @JoinColumn(name="CASHIER")
    private SystemUser cashier;
    
    @Column (name="ACTIVE")
    private boolean active;
    
    @Column (name="TYPESESSION")
    private int typeSession;
    
    protected DeliveryMealSession() {
       //for ORM only
    }
    
    //real constructor
    public DeliveryMealSession(POS pos) {
        this.pos = pos;
        this.cashier = pos.cashier();
        this.sessionDate = new DeliverySessionDate((Calendar) DateTime.now());
        this.defineSessionType();
        this.active = true;
    }
    
    @Override
    public boolean sameAs(Object other) {
        
        boolean flag = false;
        
        if(other instanceof DeliveryMealSession) {
            if(((DeliveryMealSession) other).id() == this.id())
            flag = true;
        }      
        
        return flag;
    }

    /**
     * Returns the identifier of the delivery meal session
     * @return 
     */
    @Override
    public Long id() {
        return this.idSession;
    }

    public DeliverySessionDate sessionDate(){
        return this.sessionDate;
    }
    /**
     * 
     * @return 
     */
    public POS loggedPOS(){
        return this.pos;
    }
    
    /**
     * Define the type of the session
     */
    private void defineSessionType () {
        
        int type = -1;
        
        //see what is the hour to set the correct type of session LUNCH OR DINNER
        
        //check if its lunch
        if(this.sessionDate.Hour() >= 12 && this.sessionDate.Hour() < 15) type = 1;
        
        //check if its dinner
        if(this.sessionDate.Hour() >= 18 && this.sessionDate.Hour() < 23) type = 2;
        
        //change the type of session on previous conditions
        switch(type) {
            case 1:
                this.typeSession = 1;
            break;
            
            case 2:
                this.typeSession = 2;
            break;
            default:
                this.typeSession = 0;
            break;
        }
    }
    
    /**
     * Check if its a lunch session
     * @return 
     */
    public boolean isLunch() {
        
        boolean r = false;
        
        if(this.typeSession == 1) r = true;
        
        return r;
    }
    
    /**
     * Check if the session is dinner session
     * @return 
     */
    public boolean isDinner() {
        
        boolean r = false;
        
        if(this.typeSession == 2) r = true;
        
        return r;
    }
    
    /**
     * Returns the sessions date
     * @return 
     */
    public Calendar date() {
       
        return this.sessionDate.calendar();
    }
    
    /**
     * Returns the current pos of this session
     * @return 
     */
    public POS pos(){
        
        return this.pos;
    }
    
    /**
     * Closes session
     */
    public void closeSession() {
        if(this.active) this.active = false;
        this.pos.changeState();
    }
    
    /**
     * check if session is active
     */
    public boolean isActive() {
        
        return this.active;
    }
}
