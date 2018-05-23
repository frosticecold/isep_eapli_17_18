package eapli.ecafeteria.app.user.console.presentation.authz;

import eapli.ecafeteria.application.cafeteriauser.SignupController;
import eapli.ecafeteria.domain.authz.EmployeeStrategy;
import eapli.ecafeteria.domain.authz.MecanographicNumberValidationContext;
import eapli.ecafeteria.domain.authz.StudentStrategy;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestUI extends AbstractUI {

    private final SignupController theController = new SignupController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final String mecanographicNumber = Console.readLine("Mecanographic Number:");
        MecanographicNumberValidationContext validationContext = null;
        String type = Objects.requireNonNull(Console.readLine("Insert your role(Student or Employee)")).toLowerCase();

        if ("employee".equalsIgnoreCase(type)) {
            validationContext = new MecanographicNumberValidationContext(new EmployeeStrategy(), mecanographicNumber);
        }

        if ("student".equalsIgnoreCase(type)) {
            validationContext = new MecanographicNumberValidationContext(new StudentStrategy(), mecanographicNumber);
        }

        if (validationContext != null) {
            if (validationContext.validateUser()) {
                try {
                    this.theController.signup(userData.username(), userData.password(),
                            userData.firstName(), userData.lastName(), userData.email(),
                            mecanographicNumber);
                } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                    Logger.getLogger(SignupRequestUI.class.getName()).log(Level.SEVERE, null, e);
                }
                return false;
            } else {
                throw new IllegalArgumentException("Mecanographic number is invalid!");
            }
        } else {
            throw new IllegalArgumentException("No strategy has been defined!");
        try {
            this.theController.signup(userData.userType(), userData.username(), userData.password(),
                    userData.firstName(), userData.lastName(), userData.email(),
                    mecanographicNumber);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(SignupRequestUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
