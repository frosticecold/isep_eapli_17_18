package eapli.ecafeteria.application.previsions_reporting;

import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByDateController implements Controller{
    
    private PrevisionsService service;
    
    public DeliveredMealsByDateController() {
        
        this.service = new PrevisionsService();
    }
    
}
