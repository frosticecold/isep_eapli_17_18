/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.BalanceLimits;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.BalanceLimitsController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.framework.util.Console;

/**
 *
 * @author Rúben Santos<1161391@isep.ipp.pt>
 */
public class BalanceLimitsUI extends CafeteriaUserBaseUI {
    
    BalanceLimitsController controller = new BalanceLimitsController();

    @Override
    protected boolean doShow() {
        final String limits = Console.readLine("Enter the value at which you should be warned that your balance has reached:");
        try {
            double balanceLimit = Double.valueOf(limits);
            boolean defined = controller.defineNewBalanceLimits(balanceLimit);
            if(!defined){
                System.out.println(String.format("Your balance limit must be a value over 0.0€!\n", (Object) null));
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.println(String.format("The value inserted must be a number!\n", (Object) null));
            return false;
        }
        System.out.println(String.format("Your balance limit was successfuly updated!\n", (Object) null));
        return true;
    }

    @Override
    public String headline() {
        return super.headline();
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}