/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.ElaborateOrEditMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ElaborateOrEditMenuUI extends AbstractUI {

    private ElaborateOrEditMenuController theController = new ElaborateOrEditMenuController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String headline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
