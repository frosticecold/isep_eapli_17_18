package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
@Entity
public class DeliveryRegistry implements AggregateRoot<Long>, Serializable {

    /**
     * identifier of the delivery
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "DELIVERYID")
    private Long id;

    /**
     * booking
     */
    @OneToOne
    @JoinColumn(name = "BOOKING")
    private Booking booking;

    /**
     * CLIENT
     */
    @OneToOne
    @JoinColumn(name = "CLIENT")
    private CafeteriaUser client;

    @ManyToOne
    @JoinColumn(name = "SESSION")
    private DeliveryMealSession session;

    @Temporal(TemporalType.DATE)
    private Calendar dateMade;

    protected DeliveryRegistry() {
        //for ORM only
    }

    public DeliveryRegistry(DeliveryMealSession session, CafeteriaUser client, Booking booking) {
        this.session = session;
        this.client = client;
        this.booking = booking;
        this.dateMade = Calendar.getInstance();
    }

    /**
     * compares this object with another different id of delivery, different
     * objects
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {

        boolean flag = false;

        if (other instanceof DeliveryRegistry) {
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean is(Long otherId) {
        return AggregateRoot.super.is(otherId); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns date of the registry
     *
     * @return
     */
    public Calendar date() {

        return this.dateMade;
    }

    /**
     * Return the DeliveryRegistry ID
     *
     * @return
     */
    @Override
    public Long id() {
        return this.id;
    }

    /**
     * Returns booking
     */
    public Booking booking() {

        return this.booking;
    }

    @Override
    public String toString() {

        return "ID : " + this.id() + " CLIENT NUMBER : " + this.client.mecanographicNumber() + " BOOKING ID : " + this.booking().getIdBooking();
    }
}
