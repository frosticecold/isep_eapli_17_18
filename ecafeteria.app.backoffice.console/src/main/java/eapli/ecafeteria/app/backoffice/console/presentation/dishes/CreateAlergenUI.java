/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Car
 */
public class CreateAlergenUI extends AbstractUI {

    private final AlergenController theController = new AlergenController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
       final String name = Console.readLine("Name");
        try {
            this.theController.newAlergen(name);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter an alergen which already exists in the database.");
        }

        return false;
       
    }

    @Override
    public String headline() {
        return "Add Alergen";
    }
}
