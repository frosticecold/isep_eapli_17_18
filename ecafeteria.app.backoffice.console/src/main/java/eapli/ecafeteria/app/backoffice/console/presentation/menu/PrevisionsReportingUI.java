package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.menus.PrevisionsReportingController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PrevisionsReportingUI extends AbstractUI {

    private PrevisionsReportingController ctrl;

    public PrevisionsReportingUI() {

    }

    @Override
    protected boolean doShow() {
        this.ctrl.prepareService();
        
        System.out.println("doShow");
        
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
