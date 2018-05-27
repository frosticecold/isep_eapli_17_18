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
        boolean stat = false;
        do {
            final String mecanographicNumber = Console.readLine("Mecanographic Number:");
            MecanographicNumberValidationContext validationContext = null;

            if ("employee".equalsIgnoreCase(userData.userType())) {
                validationContext = new MecanographicNumberValidationContext(new EmployeeStrategy(), mecanographicNumber);
            }

            if ("student".equalsIgnoreCase(userData.userType())) {
                validationContext = new MecanographicNumberValidationContext(new StudentStrategy(), mecanographicNumber);
            }

            if (validationContext != null) {
                if (validationContext.validateUser()) {
                    try {
                        this.theController.signup(userData.userType(), userData.username(), userData.password(),
                                userData.firstName(), userData.lastName(), userData.email(),
                                mecanographicNumber);
                    } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                        Logger.getLogger(SignupRequestUI.class.getName()).log(Level.SEVERE, null, e);
                    }
                    stat = true;
                } else {
                    Logger.getLogger(SignupRequestUI.class.getName()).log(Level.WARNING, "Mecanografic number is invalid!");
                }
            } else {
                Logger.getLogger(SignupRequestUI.class.getName()).log(Level.WARNING, "No strategy has been defined!");
            }
        } while (!stat);
        return false;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
