package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.previsions_reporting.BookedMealsReportingController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class BookedMealsReportingUI extends AbstractUI {

    private BookedMealsReportingController ctrl;

    public BookedMealsReportingUI() {
        this.ctrl = new BookedMealsReportingController();
    }

    @Override
    protected boolean doShow() {

        this.ctrl.prepareService();

        System.out.println(this.ctrl.getBookedMeals());

        return true;

    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
