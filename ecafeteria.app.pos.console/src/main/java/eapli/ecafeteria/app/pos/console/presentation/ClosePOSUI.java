/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.ClosePOSController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.DateTime;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author André Oliveira 1040862
 */
public class ClosePOSUI extends AbstractUI {

    private final ClosePOSController controller = new ClosePOSController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Map<DishType, Long> map = controller.listDeliveredMeals(DateTime.
                parseDate("07-05-2018"), controller.checkMealype());
        String output = "";
        for (Entry<DishType, Long> e : map.entrySet()) {
            output += e.getKey() + " : " + e.getValue() + "\n";
        }
        System.out.println(output);
        try {
                controller.closeDeliveryMealSession();
                System.out.println("Delivery Meal Session successfully closed.");
            } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                System.out.println("Error inserting into database.: " + ex.getMessage());
            }
        controller.closeSession();
        return true;
    }

    @Override
    public String headline() {
        return "Delivery Report";
    }
}
