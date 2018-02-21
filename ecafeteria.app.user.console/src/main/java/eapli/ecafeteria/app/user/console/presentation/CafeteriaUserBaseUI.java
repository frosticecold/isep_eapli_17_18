/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.application.bookings.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {

    protected abstract CafeteriaUserBaseController controller();

    public String showBalance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + controller().balance().toString();
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   " + showBalance();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
