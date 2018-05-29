package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.authz.Name;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.strategy.MechanographicValidator;
import eapli.framework.domain.EmailAddress;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import eapli.framework.util.Strings;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * A Signup Request
 *
 * This class represents the Signup Request created right after a person applies
 * for an Cafeteria User account. It follows a DDD approach where User is the
 * root entity of the Cafeteria User Aggregate and all of its properties are
 * instances of value objects. An Cafeteria User contains an User in it
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class SignupRequest implements AggregateRoot<Username>, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Version
    private Long version;
    
    @EmbeddedId
    private Username username;
    private Password password;
    private Name name;
    private EmailAddress email;

    private MecanographicNumber mecanographicNumber;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @Temporal(TemporalType.DATE)
    private Calendar createdOn;
    
    private String userType;

    public SignupRequest(final String userType, final String username, final String password, final String firstName, final String lastName,
            final String email, String mecanographicNumber) {
        this(userType, username, password, firstName, lastName, email, mecanographicNumber, DateTime.now());
    }

    public SignupRequest(final String userType, final String username, final String password, final String firstName, final String lastName,
            final String email, String mecanographicNumber, final Calendar createdOn) {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(firstName)
                || Strings.isNullOrEmpty(lastName) || Strings.isNullOrEmpty(email)
                || Strings.isNullOrEmpty(mecanographicNumber) || Strings.isNullOrEmpty(userType)) {
            throw new IllegalArgumentException();
        }
        this.userType = userType;
        this.username = new Username(username);
        this.password = new Password(password);
        this.name = new Name(firstName, lastName);
        this.email = EmailAddress.valueOf(email);
        this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
        // by default
        this.approvalStatus = ApprovalStatus.PENDING;
        this.createdOn = createdOn;
    }

    protected SignupRequest() {
        // for ORM only
    }

    public void accept() {
        this.approvalStatus = ApprovalStatus.ACCEPTED;
    }

    public void refuse() {
        this.approvalStatus = ApprovalStatus.REFUSED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SignupRequest)) {
            return false;
        }

        final SignupRequest other = (SignupRequest) o;

        return this.username.equals(other.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof SignupRequest)) {
            return false;
        }

        final SignupRequest that = (SignupRequest) other;
        if (this == that) {
            return true;
        }
        
        if(!this.userType.equals(that.userType)) {
            return false;
        }
        if (!this.username.equals(that.username)) {
            return false;
        }
        if (!this.password.equals(that.password)) {
            return false;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.email.equals(that.email)) {
            return false;
        }

        if (!this.mecanographicNumber.equals(that.mecanographicNumber)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean is(Username id) {
        return id().equals(id);
    }

    public MecanographicNumber mecanographicNumber() {
        return this.mecanographicNumber;
    }

    @Override
    public Username id() {
        return this.username;
    }

    public Username username() {
        return this.username;
    }

    public Name name() {
        return this.name;
    }

    public boolean isPending() {
        return this.approvalStatus == ApprovalStatus.PENDING;
    }

    public EmailAddress email() {
        return this.email;
    }

    public Password password() {
        return this.password;
    }
    
    public String userType() {
        return this.userType;
    }
}
