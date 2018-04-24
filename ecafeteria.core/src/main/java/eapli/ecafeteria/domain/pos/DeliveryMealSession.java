package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.meal.MealType;
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
        
    //@Temporal(TemporalType.DATE)
    private DeliverySessionDate sessionDate;
    
    @Transient
    private POS pos; //it wont be persisted on database
    
    private MealType typeSession;
    
    protected DeliveryMealSession() {
       //for ORM only
    }
    
    //real constructor

    public DeliveryMealSession(POS pos) {
        this.pos = pos;
        this.sessionDate = new DeliverySessionDate((Calendar) DateTime.now());
        this.defineSessionType();
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
                this.typeSession = MealType.LUNCH;
            break;
            
            case 2:
                this.typeSession = MealType.DINNER;
            break;
        }
    }
    
    /**
     * Check if its a lunch session
     * @return 
     */
    public boolean isLunch() {
        
        boolean r = false;
        
        if(this.typeSession == MealType.LUNCH) r = true;
        
        return r;
    }
    
    /**
     * Check if the session is dinner session
     * @return 
     */
    public boolean isDinner() {
        
        boolean r = false;
        
        if(this.typeSession == MealType.DINNER) r = true;
        
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
}
