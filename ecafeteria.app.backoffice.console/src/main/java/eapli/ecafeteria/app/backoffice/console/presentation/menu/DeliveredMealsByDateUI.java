package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.DeliveredMealsByDateController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByDateUI extends AbstractUI {

    private DeliveredMealsByDateController ctrl;

    public DeliveredMealsByDateUI() {

        this.ctrl = new DeliveredMealsByDateController();
    }

    @Override
    protected boolean doShow() {

        String sDate = Console.readLine("Write the date");

        Calendar date = DateTime.parseDate(sDate);

        System.out.println(this.ctrl.prepareListOfDeliveredMealsByDate(date));

        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
