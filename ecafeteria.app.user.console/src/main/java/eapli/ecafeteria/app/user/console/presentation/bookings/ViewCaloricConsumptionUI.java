/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewCaloricConsumptionUI extends CafeteriaUserBaseUI{

    private ViewCaloricConsumptionUI controller = new ViewCaloricConsumptionUI();
    
    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {
        System.out.println("TODO");
        return true;
    }
    
}
