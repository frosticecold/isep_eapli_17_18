/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
import eapli.ecafeteria.application.KitchenAlert.LimitConfigurationController;
import eapli.framework.persistence.DataConcurrencyException;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Car
 */
public class ChangeLimitUI  extends AlertUI {

    private LimitConfigurationController controller = new LimitConfigurationController();
    Scanner scanner = new Scanner(System.in);
    float tmp;
    float insert;
    @Override
    protected boolean doShow() {
        System.out.println("Value for yellow alert limit:");
        insert=scanner.nextFloat();
        tmp=insert*(float)0.01;
        try {
            controller.configureYellowLimit(tmp);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(ChangeLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChangeLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Value for red alert limit:");
        insert=scanner.nextFloat();
        tmp=insert*(float)0.01;
        try {
            controller.configureYellowLimit(tmp);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(ChangeLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChangeLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    @Override
    public String headline() {
        return super.headline() + "Change Limits";
    }
}
