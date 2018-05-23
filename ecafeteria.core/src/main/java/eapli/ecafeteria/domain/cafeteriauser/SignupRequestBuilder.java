package eapli.ecafeteria.domain.cafeteriauser;

import java.util.Calendar;

import eapli.framework.domain.ddd.DomainFactory;

/**
 * A factory for Signup Requests
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class SignupRequestBuilder implements DomainFactory<SignupRequest> {
        
        private String userType;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String mecanographicNumber;
	private Calendar createdOn;

        public SignupRequestBuilder withUserType(String userType) {
            this.userType = userType;
            return this;
        }
        
	public SignupRequestBuilder withUsername(String username) {
		this.username = username;
		return this;
	}

	public SignupRequestBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public SignupRequestBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public SignupRequestBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SignupRequestBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public SignupRequestBuilder withMecanographicNumber(String mecanographicNumber) {
		this.mecanographicNumber = mecanographicNumber;
		return this;
	}

	public SignupRequestBuilder withCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	@Override
	public SignupRequest build() {
		// since the factory knows that all the parts are needed it could throw
		// an exception. however, we will leave that to the constructor
		if (this.createdOn != null) {
			return new SignupRequest(this.userType, this.username, this.password, this.firstName, this.lastName, this.email,
					this.mecanographicNumber, this.createdOn);
		} else {
			return new SignupRequest(this.userType, this.username, this.password, this.firstName, this.lastName, this.email,
					this.mecanographicNumber);
		}
	}
}
