/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafetaria.application.pos.ChargeCardController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Currency;

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
        System.out.println(String.format("Starting the charging transaction, please insert the Cafeteria User Mecanographic Number\n", null));
        final String mecanographicNumber = Console.readLine("Enter Mecanographic Number:");
        CafeteriaUser user = theController.findCafeteriaUserByMecanographicNumber(mecanographicNumber);
        System.out.println("Before Transaction: " + user.cafeteriaUserNameAndCurrentBalance());
        final double tempCredits = Console.readDouble("Please enter the amount of Credits to charge:");
        Money credits = new Money(tempCredits, Currency.getInstance("EUR"));
        boolean resultOfOperation = theController.chargeCafeteriaUserCard(user, credits);
        System.out.println("After Transaction: " + user.cafeteriaUserNameAndCurrentBalance());
        return resultOfOperation;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
