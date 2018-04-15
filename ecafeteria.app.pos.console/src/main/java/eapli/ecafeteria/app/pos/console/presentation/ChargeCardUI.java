/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafetaria.application.pos.ChargeCardController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

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
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}