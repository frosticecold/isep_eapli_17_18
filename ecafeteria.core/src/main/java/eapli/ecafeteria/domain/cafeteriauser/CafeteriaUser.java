package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.money.Money;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * A Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. A Cafeteria User references a System User
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    @Embedded
    private Balance currentBalance;

    public CafeteriaUser(SystemUser user, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
        this.currentBalance = new Balance();
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    public boolean addCredits(Money credits) {
        return this.currentBalance.addCredits(credits);
    }

    /**
     * removes the credits of the user balance
     *
     * @param credits
     * @return sucess (true) or insucess (false)
     */
    public boolean removeCredits(Money credits) {
        return this.currentBalance.removeCredits(credits);
    }

    public void updateUserBalance(Balance currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * check if there is enough money to make a transaction
     *
     * @param credits is the new value to compare to the user balance
     * @author Beatriz Ferreira
     */
    public boolean hasEnoughCredits(Money credits) {
        return this.currentBalance.hasEnoughCredits(credits);
    }

    public Balance currentBalance() {
        return currentBalance;
    }

    public String cafeteriaUserNameAndCurrentBalance() {
        return "Username: " + systemUser.id().toString() + " Current Balance: " + currentBalance.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.systemUser.sameAs(that.systemUser));
    }

    public MecanographicNumber mecanographicNumber() {
        return id();
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }
}
