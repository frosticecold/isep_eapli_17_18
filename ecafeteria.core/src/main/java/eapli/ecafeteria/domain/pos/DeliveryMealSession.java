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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long idSession;

    @Temporal(TemporalType.DATE)
    private Calendar sessionDate;

    @OneToOne
    @JoinColumn(name = "CASHIER")
    private SystemUser cashier;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "TYPESESSION")
    private int typeSession;

    protected DeliveryMealSession() {
        //for ORM only
    }

    //real constructor
    public DeliveryMealSession(POS pos) {
        this.cashier = pos.cashier();
        this.sessionDate = Calendar.getInstance();
        this.defineSessionType();
        this.active = true;
    }

    @Override
    public boolean sameAs(Object other) {

        boolean flag = false;

        if (other instanceof DeliveryMealSession) {
            if (((DeliveryMealSession) other).id() == this.id()) {
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Returns the identifier of the delivery meal session
     *
     * @return
     */
    @Override
    public Long id() {
        return this.idSession;
    }

    public Calendar sessionDate() {
        return this.sessionDate;
    }

    /**
     * Define the type of the session
     */
    private void defineSessionType() {

        int type = -1;

        //see what is the hour to set the correct type of session LUNCH OR DINNER
        //check if its lunch
        if (this.sessionDate.get(Calendar.HOUR) >= 12 && this.sessionDate.get(Calendar.HOUR) < 15) {
            type = 1;
        }

        //check if its dinner
        if (this.sessionDate.get(Calendar.HOUR) >= 18 && this.sessionDate.get(Calendar.HOUR) < 23) {
            type = 2;
        }

        //change the type of session on previous conditions
        switch (type) {
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
     *
     * @return
     */
    public boolean isLunch() {

        boolean r = false;

        if (this.typeSession == 1) {
            r = true;
        }

        return r;
    }

    /**
     * Check if the session is dinner session
     *
     * @return
     */
    public boolean isDinner() {

        boolean r = false;

        if (this.typeSession == 2) {
            r = true;
        }

        return r;
    }

    /*
     * Closes session
     */
    public void closeSession() {
        if (this.active) {
            this.active = false;
        }

    }

    /**
     * check if session is active
     */
    public boolean isActive() {

        return this.active;
    }
}
