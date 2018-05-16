package eapli.ecafeteria.application.menus;

import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PrevisionsReportingController implements Controller {

    private PrevisionsService service;

    public PrevisionsReportingController() {

    }

    /**
     * Prepare previsions service
     */
    public void prepareService() {

        this.service = new PrevisionsService();
    }
    
    /**
     * Gets the booked meals relation by dish type
     */
    public String getBookedMeals() {
        
        String msg = "------------------------------------Number of booked meals by dish type -------------------------------------\n"
                + " MEAT    |   FISH    |   VEGIE   |\n";
        
        Long[][] list = new Long [1][3];
        
        //list = this.service.prepareBookedMealsList();
        
        /*int i;
        
        for(i = 0; i < 1; i++) {
            
            msg += "    " + list[i][0] + "  |   " + list[i][1] + "  |   " + list[i][2] + "  |\n";
        }*/
        
        return msg;
    }
}
