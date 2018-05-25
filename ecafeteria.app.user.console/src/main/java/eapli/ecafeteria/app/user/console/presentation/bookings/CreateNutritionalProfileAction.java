/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.framework.actions.Action;

/**
 *
 * @author luisdematos
 */
public class CreateNutritionalProfileAction implements Action{

    @Override
    public boolean execute() {
        return new CreateNutritionalProfileUI().show();
    }    

    
}
