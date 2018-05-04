/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.ViewAvailableMealsController;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ViewAvailableMealsUI extends AbstractUI {

    private final ViewAvailableMealsController controller = new ViewAvailableMealsController();

    @Override
    protected boolean doShow() {
        System.out.println("Available Dishes:");
        AvailableMealsStatistics availableMeals = controller.findAvailableMealsPerDay();
        System.out.println(availableMeals);
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "] \n  "
                + controller.findAvailableMealsPerDay();
    }

}
