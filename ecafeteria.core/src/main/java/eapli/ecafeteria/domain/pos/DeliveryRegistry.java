package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
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
    @EmbeddedId
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="DELIVERY")
    private Long id;
    
    /**
     * POS
     */
    @OneToOne
    @JoinColumn (name="POS")
    private POS pos;
    
    /**
     * booking
     */
    @OneToOne
    @Column(name="BOOKING")
    private Booking Booking;
    
    /**
     * POS User
     */
    @OneToOne
    @JoinColumn(name="POSUSER")
    private CafeteriaUser employee;
    
    /**
     * CLIENT
     */
    @OneToOne
    @JoinColumn(name="CLIENT")
    private CafeteriaUser client;
    
    @OneToOne
    @JoinColumn(name="SESSION")
    private DeliveryMealSession session;
    
    protected DeliveryRegistry() {
        //for ORM only
    }
    
    public DeliveryRegistry(DeliveryMealSession session, POS pos, CafeteriaUser Client, Booking Booking) {
        this.client = Client;
        this.Booking = Booking;
        this.session = session;
        this.pos = pos;
        //this.employee = ;
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
   
}
