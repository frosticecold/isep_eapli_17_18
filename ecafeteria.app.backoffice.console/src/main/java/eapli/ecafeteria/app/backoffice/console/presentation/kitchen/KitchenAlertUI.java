/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
import eapli.ecafeteria.application.KitchenAlert.LimitConfigurationController;

/**
 *
 * @author Car
 */
public class KitchenAlertUI extends AlertUI {

    private LimitConfigurationController controller = new LimitConfigurationController();

    @Override
    protected boolean doShow() {
        boolean validar;
    return true;
    }

    @Override
    public String headline() {
        return super.headline() + "Kitchen Alert Configuration";
    }

}
