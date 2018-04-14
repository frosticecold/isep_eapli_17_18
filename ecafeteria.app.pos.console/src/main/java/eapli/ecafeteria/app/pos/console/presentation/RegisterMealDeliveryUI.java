/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.pos.console.application.RegisterMealDeliveryController;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryUI {
    
    private RegisterMealDeliveryController ctrl;

    /** Construtor that shall receive the entity of the open session of a certain POS **/
    public RegisterMealDeliveryUI() {
        
        //create controller
        this.ctrl = new RegisterMealDeliveryController();
    }
}
