/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.ClosePOSController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Oliveira
 */
public class ClosePOSUI extends AbstractUI {

    private final ClosePOSController controller = new ClosePOSController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
       controller.closeSession();
       controller.listDeliveredMeals();
       return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }

}
