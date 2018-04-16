package eapli.cafeteria.domain.pos;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.domain.ddd.DomainEntity;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
@Entity
public class DeliveryRegistry implements DomainEntity {

    /**
     * identifier of the delivery
     */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="DELIVERY")
    private Long id;
    
    /**
     * identifier of the pos was registered the delivery
     */
    @Column (name="POS")
    private long pos;
    
    /**
     * identifier of the booking
     */
    @Column(name="BOOKING")
    private long idBooking;
    
    /**
     * identifier of the employee
     */
    @Column(name="EMPLOYEE")
    private MecanographicNumber employee;
    
    /**
     * identifier of the client
     */
    @Column(name="CLIENT")
    private MecanographicNumber client;
    
    @Temporal(TemporalType.DATE)
    private DeliverySessionDate sessionDate;
    
    protected DeliveryRegistry() {
        //for ORM only
    }
    
    public DeliveryRegistry(DeliveryMealSession session, POS pos, MecanographicNumber Client, long Booking) {
        this.client = Client;
        this.idBooking = Booking;
        this.sessionDate = session.sessionDate();
        this.pos = pos.id();
        this.employee = pos.posUser();
    }
    /**
     * compares this object with another
     * different id of delivery, different objects
     * @param other
     * @return 
     */
    @Override
    public boolean sameAs(Object other) {
       
       boolean flag = false;
        
       if(other instanceof DeliveryRegistry) {
           if(((DeliveryRegistry) other).id == this.id) flag = true;
       }
       
       return flag;
    }

    @Override
    public boolean is(Object otherId) {
        return DomainEntity.super.is(otherId); 
    }

    /**
     * Returns the delivery id
     * @return 
     */
    @Override
    public Object id() {
        return this.id;
    }
   
    /**
     * Returns the id of the employee that did this delivery
     * @return 
     */
    public MecanographicNumber idEmployee() {
        
        return this.employee;
    }
    
    /**
     * Returns the id of the client who paid for this delivery
     * @return 
     */
    public MecanographicNumber idClient() {
        
        return this.client;
    }
    
    /**
     * Returns the id of the booking for this delivery
     * @return 
     */
    public long idBooking() {
        
        return this.idBooking;
    }
    
    /**
     * Returns the id of the pos which was done this delivery
     * @return 
     */
    public long idPos() {
        
        return this.pos;
    }
}
