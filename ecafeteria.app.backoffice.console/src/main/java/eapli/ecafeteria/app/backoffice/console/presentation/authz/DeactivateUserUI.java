/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.authz.DeactivateUserController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.reasons.ReasonType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {

    private final DeactivateUserController theController = new DeactivateUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = this.theController.activeUsers();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            int cont = 1;
            System.out.println("SELECT User to deactivate\n");
            // FIXME use select widget, see, ChangeDishTypeUI
            System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");
            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(),
                        user.name().firstName(), user.name().lastName());
                cont++;
            }
            final int option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");
            if (option == 0) {
                System.out.println("No user selected");
            } else {
                final Iterable<ReasonType> reason_iterable = theController.getAllReasons();
                SelectWidget<ReasonType> select_reason = new SelectWidget<>("Select Reason", reason_iterable);
                select_reason.show();
                int reason_option = select_reason.selectedOption();
                if (reason_option > 0) {
                    String comment = Console.readLine("Please insert a comment");
                    try {
                        this.theController.deactivateUser(list.get(option - 1), select_reason.selectedElement(), comment);
                    } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                        Logger.getLogger(DeactivateUserUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("No reason selected...");
                }
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Deactivate User";
    }
}
