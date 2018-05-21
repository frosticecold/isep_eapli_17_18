/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.ChargeCardController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.CreditTransaction.ChargeCreditsEvent;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author MarioDias
 */
public class ChargeCardUI extends AbstractUI {

    private final ChargeCardController theController = new ChargeCardController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        try {
            System.out.println(String.format("Starting the charging transaction, please insert the Cafeteria User Mecanographic Number\n", null));
            final String mecanographicNumber = Console.readLine("Enter Mecanographic Number:");

            String currentBalanceAndUser = theController.findCafeteriaUserByMecanographicNumber(mecanographicNumber);

            System.out.println("Before Transaction: " + currentBalanceAndUser);
            final double tempCredits = Console.readDouble("Please enter the amount of Credits to charge:");

            boolean resultOfOperation = theController.createMovementCharging(tempCredits);
            if (!resultOfOperation) {
                System.out.println("Error has ocurred");
                return false;
            }
            theController.saveMovementChargingTransaction();
            theController.checkMovementPersistence();

            currentBalanceAndUser = theController.findCafeteriaUserByMecanographicNumber(mecanographicNumber);
            System.out.println("After Transaction: " + currentBalanceAndUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
