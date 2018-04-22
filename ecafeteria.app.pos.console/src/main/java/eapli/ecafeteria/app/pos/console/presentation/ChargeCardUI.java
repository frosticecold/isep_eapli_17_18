/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.ChargeCardController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.CreditTransaction.CreditRecharge;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
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
        MecanographicNumber mecnum = new MecanographicNumber(mecanographicNumber);
        CafeteriaUser user = theController.findCafeteriaUserByMecanographicNumber(mecanographicNumber);
        System.out.println("Before Transaction: " + user.cafeteriaUserNameAndCurrentBalance());
        final double tempCredits = Console.readDouble("Please enter the amount of Credits to charge:");
        Money credits = new Money(tempCredits, Currency.getInstance("EUR"));
        boolean resultOfOperation = theController.chargeCafeteriaUserCard(user, credits);
        CafeteriaUser result = theController.saveCafeteriaUser(user);

        final TransactionRepository tr = PersistenceContext.repositories().transactioRepository();
        Iterable<Transaction> it = tr.findAllTransactionsByCafeteriaUserAndType(user, "Credit");

        for (Transaction transaction : it) {
            System.out.println(transaction);
        }
        
        if (result == null) {
            resultOfOperation = false;
            System.out.println("Error has occurred during the operation");
            return resultOfOperation;
        } else {
            System.out.println("After Transaction: " + user.cafeteriaUserNameAndCurrentBalance());
        }
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
