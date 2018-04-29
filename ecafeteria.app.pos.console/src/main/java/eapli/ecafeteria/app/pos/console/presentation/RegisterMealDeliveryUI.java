package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.RegisterMealDeliveryController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryUI extends AbstractUI {
    
    private final RegisterMealDeliveryController ctrl;

    /** Construtor that shall receive the entity of the open session of a certain POS
     * @param session **/
    public RegisterMealDeliveryUI() { 
        this.ctrl = new RegisterMealDeliveryController();
    }
    
    /**
     * 
     * @param mecNumber - MecanographicNumber of the client
     * @param booking - id of the booking to register
     */
    private void recordNewMealDelivery(String mecNumber, long idBooking)  {

        //verifies if user is viable or active
        if(!this.ctrl.validateClient(mecNumber)) {
            System.out.println("User doesnt exists!MecanographicNumber doesnt exist!");
            return;
        }
            if(!this.ctrl.validatesBooking(idBooking)) System.out.println("This booking doesnt exist!");
            else  {
                if(!this.ctrl.canServeBooking(idBooking)) {
                    //if theres isnt any issue is the validation of entities
                    try{
                        this.ctrl.registerNewMealDelivery(mecNumber, idBooking);
                        System.out.println("Register done");
                    }
                    catch (Exception e) {
                        System.out.println("Database error:" + e.getMessage());
                   }
                }
                else System.out.println("Booking already served!");
            }
    }

    @Override
    protected boolean doShow() {
        
        try { 
            final String mecaNumber = Console.readLine("Insert Mecanographic Number of client:");
            final long idBooking = Console.readLong("Insert number of booking:");
        
            this.recordNewMealDelivery(mecaNumber, idBooking);
        }
        catch (Exception e) {
            
            return false;
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
