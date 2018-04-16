package eapli.cafeteria.domain.pos;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDDELIVERYEALSESSION")
    private Long idSession;
        
    @Temporal(TemporalType.DATE)
    private DeliverySessionDate sessionDate;
    
    @OneToMany
    private DeliveryRegistry registry;
    
    @Transient
    private POS pos; //it wont be persisted on database
    
    protected DeliveryMealSession() {
       //for ORM only
    }
    
    //real constructor

    public DeliveryMealSession(POS pos) {
        this.pos = pos;
        this.sessionDate = new DeliverySessionDate((Calendar) DateTime.now());
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
   
    /**
     * Returns this delivery sessions date
     * @return 
     */
    public DeliverySessionDate sessionDate() {
        
        return this.sessionDate;
    }
    
}
